package com.example.memory.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final long DEFAULT_EXPIRATION = 60; // 1 minutes

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Retrieves a value from the cache.
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * Puts a value into the cache with a default expiration time.
     */
    public void put(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, DEFAULT_EXPIRATION, TimeUnit.SECONDS);
    }

    /**
     * Puts a value into the cache only if the key is not already present.
     */
    public boolean putIfAbsent(String key, Object value) {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value, DEFAULT_EXPIRATION, TimeUnit.SECONDS);
        return result != null && result;
    }

    /**
     * Evicts a key from the cache.
     */
    public void evict(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Evicts a key only if it exists.
     */
    public boolean evictIfPresent(String key) {
        Boolean exists = redisTemplate.hasKey(key);
        if (Boolean.TRUE.equals(exists)) {
            return Boolean.TRUE.equals(redisTemplate.delete(key));
        }
        return false;
    }

    /**
     * Tries to acquire a lock on the given key.
     * Returns true if the lock is acquired, false otherwise.
     */
    public boolean acquireLock(String key, String value) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, value, 30, TimeUnit.SECONDS); // 30 sec lock
        return Boolean.TRUE.equals(success);
    }

    /**
     * Releases the lock by deleting the key.
     */
    public void releaseLock(String key) {
        redisTemplate.delete(key);
    }
}
