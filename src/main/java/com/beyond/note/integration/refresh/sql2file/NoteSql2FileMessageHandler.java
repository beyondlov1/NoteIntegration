package com.beyond.note.integration.refresh.sql2file;

import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.refresh.FileUtil;
import com.beyond.note.integration.refresh.Sql2FileMetaData;
import com.beyond.sync.utils.JsonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class NoteSql2FileMessageHandler implements MessageHandler {


    @SuppressWarnings("unchecked")
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof List) {
            List<Note> notes = (List<Note>) payload;
            for (Note note : notes) {
                File dataFile = new File("./file/data/" + generateTitleForFile(note) + ".md");
                writeToFile(note,dataFile);
            }
        }
    }

    private void writeToFile(Note note, File dataFile) {
        if (dataFile.exists() && FileUtil.hasAttribute(dataFile,"metadata")) {
            // 文件存在, 判断是否需要更新
            try {
                String sql2FileMetaDataStr = FileUtil.readFileUserAttribute(dataFile,"metadata");
                Sql2FileMetaData sql2FileMetaData = JsonUtil.decode(sql2FileMetaDataStr, Sql2FileMetaData.class);
                if (StringUtils.equals(sql2FileMetaData.getId(),note.getId())){
                    Date fileLastModifyTime = sql2FileMetaData.getLastModifyTime();
                    Date sqlLastModifyTime = note.getLastModifyTime();
                    Date contentLastModifiedTime = new Date(Files.getLastModifiedTime(dataFile.toPath()).toMillis());
                    if (DateUtils.truncatedCompareTo(contentLastModifiedTime, sql2FileMetaData.getLastRefreshTime() == null?new Date(0):sql2FileMetaData.getLastRefreshTime(), Calendar.SECOND) == 0) {
                        // 本地未更改过文件
                        if (DateUtils.truncatedCompareTo(fileLastModifyTime, sqlLastModifyTime, Calendar.MILLISECOND) < 0) {
                            // 需要更新
                            doWriteToFile(note, dataFile);
                        }
                    }
                }else {
                    // 文件名冲突, 添加id作为区别
                    String filePath = dataFile.getPath();
                    String newPath = StringUtils.substringBeforeLast(filePath, ".") + "-" +note.getId()+".md";
                    dataFile = new File(newPath);
                    writeToFile(note, dataFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 文件不存在, 创建并更新
            if ( !dataFile.exists() && !FileUtil.hasAttribute(dataFile,"metadata")){
                doWriteToFile(note, dataFile);
            }
        }
    }

    private void doWriteToFile(Note note, File dataFile) {
        Sql2FileMetaData sql2FileMetaData = new Sql2FileMetaData();
        BeanUtils.copyProperties(note, sql2FileMetaData);
        sql2FileMetaData.setLastRefreshTime(sql2FileMetaData.getLastModifyTime());
        try {
            FileUtils.writeStringToFile(dataFile,
                    note.getContent(),
                    Charset.defaultCharset());
            FileUtil.writeFileUserAttribute(dataFile,
                    "metadata",
                    JsonUtil.encode(sql2FileMetaData, Sql2FileMetaData.class));
            Files.getFileAttributeView(dataFile.toPath(), BasicFileAttributeView.class)
                    .setTimes(
                            FileTime.fromMillis(note.getLastModifyTime().getTime()),
                            null,
                            FileTime.fromMillis(note.getCreateTime().getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateTitleForFile(Note note) {
        String title = subReplaceSpecChars(note.getTitle());
        if (StringUtils.isBlank(title)){
            String content = subReplaceSpecChars(note.getContent());
            if (StringUtils.isBlank(content)) {
                return note.getId();
            }
            return content;
        }
        return title;
    }

    private String generateFileName(Note note) {
        String title = subReplaceSpecChars(note.getTitle());
        if (StringUtils.isBlank(title)) {
            String content = subReplaceSpecChars(note.getContent());
            if (StringUtils.isBlank(content)) {
                return note.getId();
            }
            return content + "-" + note.getId();
        }
        return title + "-" + note.getId();
    }

    private String subReplaceSpecChars(String target) {
        return StringUtils.substring(
                StringUtils.trimToEmpty(target)
                        .replaceAll("[/\\\\:*?|]", ".")
                        .replaceAll("[\"<>]", "'")
                        .replaceAll("[#-]", "")
                        .replace("\n", ""), 0, 15);
    }
}
