package com.beyond.note.integration.utils;

/**
 * @author: beyond
 * @date: 2019/8/19
 */

public interface Serializer<S,T> {
    S encode(T t);
    T decode(S s);
}
