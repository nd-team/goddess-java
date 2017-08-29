package com.bjike.goddess.redis.client;

import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-18 11:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class RedisClientImpl implements RedisClient {

    private static final String MSG = "服务不可用";

    @Resource(name = "jedisPools")
    private JedisPool jedisPool;

    public void save(String key, String value) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void save(String key, String value, int seconds) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

    }

    @Override
    public String get(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void remove(String... keys) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(keys);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void append(String key, String value) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.append(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void appendToList(String key, String... values) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.rpush(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

    }

    @Override
    public void appendToList(String key, int seconds, String... values) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.rpush(key, values);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

    }

    @Override
    public void removeToList(String key, String value) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.lrem(key, 0, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void appendToMap(String key, String field, String value, int seconds) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hset(key, field, value);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void appendToMap(String key, String field, String value) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void saveMap(String key, Map<String, String> map) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hmset(key, map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void saveMap(String key, Map<String, String> map, int seconds) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hmset(key, map);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> getMap(String key, String... fields) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hmget(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public String getMap(String key, String field) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hmget(key, field).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public Map<String, String> getAllMap(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void removeMap(String key, String... fields) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hdel(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

    }

    @Override
    public Long mapLength(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hlen(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public Boolean exists(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> mapKeys(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hkeys(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> mapValues(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.hvals(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }


    @Override
    public void saveList(String key, List<String> values) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            values.stream().forEach(val -> {
                jedis.lpush(key, val);

            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void saveList(String key, List<String> values, int seconds) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            values.stream().forEach(val -> {
                jedis.lpush(key, val);
                jedis.expire(key, seconds);

            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> getList(String key, int start, int end) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.lrange(key, start, end);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public List<String> getList(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.lrange(key, 0, -1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }


    @Override
    public void saveSet(String key, Set<String> values) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            values.stream().forEach(val -> {
                jedis.sadd(key, val);
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public Set<String> getSet(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.smembers(key);

        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public void removeSet(String key, String values) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.srem(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public String getRandomValueBySet(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.srandmember(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    @Override
    public Long setLength(String key) throws SerException {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.scard(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(MSG);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }


}
