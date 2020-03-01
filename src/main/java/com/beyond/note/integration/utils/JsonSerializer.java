package com.beyond.note.integration.utils;


import com.beyond.sync.entity.Traceable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: beyond
 * @date: 2019/8/19
 */

public class JsonSerializer<T extends Traceable> implements Serializer<String, T> {

    private Class<T> clazz;
    private ObjectMapper objectMapper;

    public JsonSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String encode(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T decode(String s) {
        if (s == null) {
            return null;
        }
        try {
            return objectMapper.readValue(s, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
