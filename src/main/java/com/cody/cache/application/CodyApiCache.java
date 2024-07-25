package com.cody.cache.application;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CodyApiCache {

    private final ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    public <T> T getObject(String key, Class<T> type) {
        return (T) cache.get(key);
    }

    public <T> List<T> getList(String key, Class<T> type) {
        return (List<T>) cache.get(key);
    }

    public <T> void put(String key, T value) {
        cache.put(key, value);
    }

    public void remove(String key) {
        cache.remove(key);
    }

}
