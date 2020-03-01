package com.beyond.note.integration.sync;

import com.beyond.sync.utils.IDUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;

@Component
public class VirtualKeyManager implements InitializingBean {

    private String key;

    public String getKey() {
        return key;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        File keyFile = new File("./my.key");
        if (keyFile.exists()){
            key = FileUtils.readFileToString(keyFile, Charset.defaultCharset());
        }else {
            String uuid = IDUtil.uuid();
            FileUtils.writeStringToFile(keyFile, uuid,Charset.defaultCharset());
            key = uuid;
        }
    }
}
