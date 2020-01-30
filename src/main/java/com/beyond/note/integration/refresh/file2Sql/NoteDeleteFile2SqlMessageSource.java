package com.beyond.note.integration.refresh.file2Sql;

import com.beyond.note.integration.refresh.Sql2FileMetaData;
import com.beyond.sync.utils.JsonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class NoteDeleteFile2SqlMessageSource implements MessageSource<List<String>> {

    @Override
    public Message<List<String>> receive() {
        List<String> deletedIds = new ArrayList<>();
        File dataDirectory = new File("./file/metadata");
        if (!dataDirectory.exists()) {
            return new GenericMessage<>(Collections.emptyList());
        }
        Collection<File> files = FileUtils.listFiles(dataDirectory, new String[]{"metadata"}, true);
        for (File file : files) {
            try {
                String dataFilePath = file.getParentFile().getParent() + "/data/" + StringUtils.substringBeforeLast(file.getName(), ".");
                if (!new File(dataFilePath).exists()){
                    String metaDataStr = FileUtils.readFileToString(file, Charset.defaultCharset());
                    Sql2FileMetaData metaData = JsonUtil.decode(metaDataStr, Sql2FileMetaData.class);
                    deletedIds.add(metaData.getId());
                }

            } catch (IOException e) {
               e.printStackTrace();
            }
        }
        return new GenericMessage<>(deletedIds);
    }

}
