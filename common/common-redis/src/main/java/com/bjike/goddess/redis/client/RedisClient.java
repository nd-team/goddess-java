package com.bjike.goddess.redis.client;

import com.bjike.goddess.common.api.exception.SerException;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * key 不重复，值会覆盖更新
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-18 10:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public interface RedisClient {


    /**
     * 保存
     *
     * @param key
     * @param value
     * @throws SerException
     */
    default void save(String key, String value) throws SerException {

    }

    /**
     * 保存
     *
     * @param key
     * @param value
     * @param seconds 保存时间
     * @throws SerException
     */
    default void save(String key, String value, int seconds) throws SerException {

    }

    /**
     * 获取
     *
     * @param key
     * @return
     * @throws SerException
     */
    default String get(String key) throws SerException {
        return null;
    }


    /**
     * 删除某个redis值
     *
     * @param keys
     * @throws SerException
     */
    default void remove(String... keys) throws SerException {
    }

    /**
     * 追加
     *
     * @param key
     * @param value 追加值
     * @throws SerException
     */
    default void append(String key, String value) throws SerException {

    }

    /**
     * 追加
     *
     * @param key
     * @param values 追加值
     * @throws SerException
     */
    default void appendToList(String key, String... values) throws SerException {

    }

    /**
     * 追加
     *
     * @param key
     * @param seconds 时间
     * @param values  追加值
     * @throws SerException
     */
    default void appendToList(String key, int seconds, String... values) throws SerException {

    }

    /**
     * 删除某元素
     *
     * @param key
     * @param value 删除值
     * @throws SerException
     */
    default void removeToList(String key, String value) throws SerException {

    }


    /**
     * 追加
     *
     * @param key   不重复
     * @param value 追加值
     * @throws SerException
     */
    default void appendToMap(String key, String field, String value) throws SerException {

    }

    /**
     * 追加
     *
     * @param key     不重复
     * @param value   追加值
     * @param seconds 时间
     * @throws SerException
     */
    default void appendToMap(String key, String field, String value, int seconds) throws SerException {

    }

    /**
     * @param key 存入redis的key，不重复
     * @param map 保存多个key val
     * @throws SerException
     */
    default void saveMap(String key, Map<String, String> map) throws SerException {

    }

    /**
     * @param key     存入redis的key，不重复
     * @param map     保存多个key val
     * @param seconds 时间
     * @throws SerException
     */
    default void saveMap(String key, Map<String, String> map, int seconds) throws SerException {

    }

    /**
     * @param key    存入redis中map对象的key
     * @param fields 放入map中的对象的key
     * @return
     * @throws SerException
     */
    default List<String> getMap(String key, String... fields) throws SerException {
        return null;
    }

    /**
     * @param key    存入redis中map对象的key
     * @param field 放入map中的对象的key
     * @return
     * @throws SerException
     */
    default String getMap(String key, String field) throws SerException {
        return null;
    }

    /**
     * @param key 存入redis中map对象的key
     * @return
     * @throws SerException
     */
    default Map<String, String> getAllMap(String key) throws SerException {
        return null;
    }

    /**
     * 删除map的key，值
     *
     * @param key    保存到redis的key
     * @param fields
     * @throws SerException
     */
    default void removeMap(String key, String... fields) throws SerException {
    }


    /**
     * 获取map长度
     *
     * @param key 保存到redis的key
     * @return
     * @throws SerException
     */
    default Long mapLength(String key) throws SerException {
        return null;
    }

    /**
     * 是否存在
     *
     * @param key 保存到redis的key
     * @return
     * @throws SerException
     */
    default Boolean exists(String key) throws SerException {
        return null;
    }

    /**
     * 获取map的所有key
     *
     * @param key
     * @return
     * @throws SerException
     */
    default Set<String> mapKeys(String key) throws SerException {
        return null;
    }

    /**
     * 获取map的所以value
     *
     * @param key
     * @return
     * @throws SerException
     */
    default List<String> mapValues(String key) throws SerException {
        return null;
    }

    /**
     * 保存列表
     *
     * @param key
     * @param values
     * @throws SerException
     */
    default void saveList(String key, List<String> values) throws SerException {

    }

    /**
     * 保存列表
     *
     * @param key
     * @param seconds 时间
     * @param values
     * @throws SerException
     */
    default void saveList(String key, List<String> values, int seconds) throws SerException {

    }

    /**
     * 获取list集合
     *
     * @param key
     * @param start 起始坐标
     * @param end   结束坐标
     * @return
     * @throws SerException
     */
    default List<String> getList(String key, int start, int end) throws SerException {
        return null;
    }

    /**
     * 获取list集合
     *
     * @param key
     * @return
     * @throws SerException
     */
    default List<String> getList(String key) throws SerException {
        return null;
    }

    /**
     * 添加set集合
     *
     * @param key
     * @param values
     * @throws SerException
     */
    default void saveSet(String key, Set<String> values) throws SerException {
    }

    /**
     * 获取set的值列表
     *
     * @param key
     * @return
     * @throws SerException
     */
    default Set<String> getSet(String key) throws SerException {
        return null;
    }

    /**
     * 删除set的值列表
     *
     * @param key
     * @throws SerException
     */
    default void removeSet(String key, String values) throws SerException {

    }


    /**
     * 随机获取set的随机数
     *
     * @param key
     * @return
     * @throws SerException
     */
    default String getRandomValueBySet(String key) throws SerException {
        return null;
    }

    /**
     * 获取set的大小
     *
     * @param key
     * @return
     * @throws SerException
     */
    default Long setLength(String key) throws SerException {
        return null;
    }

    default JedisPool getJedisPool() {
        return null;
    }

    default void setJedisPool(JedisPool jedisPool) {
    }

}
