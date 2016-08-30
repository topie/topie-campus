package com.topie.campus.common;

/**
 * Created by chenguojun on 8/30/16.
 */
public interface IBasicCache<K, V> {

    void set(K key, V value);

    void set(K key, V value, int seconds);

    V get(K key);

    void del(K key);

    void expire(K key, int seconds);
}
