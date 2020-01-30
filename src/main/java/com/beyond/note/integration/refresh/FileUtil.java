package com.beyond.note.integration.refresh;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileUtil {
    public static String readFileUserAttribute(File dataFile, String attr) throws IOException {
        String parent = dataFile.getParentFile().getParent();
        return FileUtils.readFileToString(new File(parent + "/" + attr+ "/" + dataFile.getName()+"."+attr), Charset.defaultCharset());
//        Map<String, Object> sql2FileMetaDataMap = Files.readAttributes(dataFile.toPath(), "user:"+attr);
//        byte[] metadataBytes = (byte[]) sql2FileMetaDataMap.get(attr);
//        return new String(metadataBytes);
    }

    public static void writeFileUserAttribute(File dataFile, String attr, String content) throws IOException {
        String parent = dataFile.getParentFile().getParent();
        FileUtils.writeStringToFile(new File(parent + "/" + attr+ "/" + dataFile.getName()+"."+attr), content,Charset.defaultCharset());
//        Files.setAttribute(file.toPath(), "user:"+attr, content.getBytes());
    }

    public static boolean hasAttribute(File dataFile, String attr){
        String parent = dataFile.getParentFile().getParent();
        return new File(parent + "/" + attr + "/" + dataFile.getName() + "." + attr).exists();
    }
}
