package com.beyond.note.integration.refresh.file2Sql;

import com.beyond.note.integration.entity.Note;
import com.beyond.note.integration.refresh.FileUtil;
import com.beyond.note.integration.refresh.Sql2FileMetaData;
import com.beyond.sync.utils.JsonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;

@Component
public class NoteFile2SqlMessageSource implements MessageSource<List<Note>> {

    @Override
    public Message<List<Note>> receive() {
        List<Note> notes = new ArrayList<>();
        File dataDirectory = new File("./file/data");
        if (!dataDirectory.exists()) {
            return new GenericMessage<>(Collections.emptyList());
        }
        Collection<File> files = FileUtils.listFiles(dataDirectory, new String[]{"md"}, true);
        for (File file : files) {
            try {
                String metaDataStr = FileUtil.readFileUserAttribute(file, "metadata");
                Sql2FileMetaData metaData = JsonUtil.decode(metaDataStr, Sql2FileMetaData.class);

                // 处理源信息
                Note note = new Note();
                BeanUtils.copyProperties(metaData, note);
                note.setContent(FileUtils.readFileToString(file, Charset.defaultCharset()));

                // 更新修改时间
                FileTime lastModifiedTime = Files.getLastModifiedTime(file.toPath());
                note.setLastModifyTime(new Date(lastModifiedTime.toMillis()));
                notes.add(note);
            } catch (IOException e) {
                if (e instanceof NoSuchFileException || e instanceof FileNotFoundException) {
                    try {
                        Note note = Note.create();
                        note.setTitle(StringUtils.substringBeforeLast(file.getName(),"."));
                        note.setContent(FileUtils.readFileToString(file, Charset.defaultCharset()));
                        BasicFileAttributes basicFileAttributes = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class).readAttributes();
                        FileTime creationTime = basicFileAttributes.creationTime();
                        FileTime lastModifiedTime = basicFileAttributes.lastModifiedTime();
                        note.setCreateTime(new Date(creationTime.toMillis()));
                        note.setLastModifyTime(new Date(lastModifiedTime.toMillis()));
                        notes.add(note);
                        updateMetadata(file,note);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    e.printStackTrace();
                }
            }
        }
        return new GenericMessage<>(notes);
    }

    private void updateMetadata(File file, Note note) throws IOException {
        Sql2FileMetaData sql2FileMetaData = new Sql2FileMetaData();
        BeanUtils.copyProperties(note,sql2FileMetaData);
        sql2FileMetaData.setLastRefreshTime(sql2FileMetaData.getLastModifyTime());
        FileUtil.writeFileUserAttribute(file,"metadata", JsonUtil.encode(sql2FileMetaData,Sql2FileMetaData.class));
    }
}
