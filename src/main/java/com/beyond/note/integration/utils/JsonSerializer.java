package com.beyond.note.integration.utils;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.beyond.sync.entity.Traceable;

import java.lang.reflect.Type;

/**
 * @author: beyond
 * @date: 2019/8/19
 */

public class JsonSerializer<T extends Traceable> implements Serializer<String, T> {

    private Class<T> clazz;

    public JsonSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String encode(T t) {
        return JSONObject.toJSONString(t);
    }

    @Override
    public T decode(String s) {
        if (s == null) {
            return null;
        }
        try {
            return JSONObject.parseObject(s, (Type) clazz);
        } catch (JSONException e) {
            return null;
        }
    }
}
