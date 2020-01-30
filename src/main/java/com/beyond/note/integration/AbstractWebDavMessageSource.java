package com.beyond.note.integration;


import com.beyond.sync.datasouce.webdav.support.DavClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.io.IOException;

public abstract class AbstractWebDavMessageSource<T> implements MessageSource<T> {

    private ObjectMapper objectMapper;

    public AbstractWebDavMessageSource() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Message<T> receive() {
        DavClient davClient = getDavClient();
        try {
            String content = davClient.get(url());
            T t = objectMapper.readValue(content, clazz());
            return new GenericMessage<>(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract Class<T> clazz();

    protected abstract DavClient getDavClient();

    protected abstract String url();
}
