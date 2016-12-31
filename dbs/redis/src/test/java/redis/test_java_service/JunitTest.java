package redis.test_java_service;


import mongo.test_java_service.code.ApplicationConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by lgq on 16-10-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest {
    private Jedis jedis;

    @Before
    public void init() {
        jedis = new Jedis("localhost", 6379);
    }

    @Test
    public void testAdd() {
        jedis.set("name", "xinxin");//向key-->name中放入了value-->xinxin
        System.out.println(jedis.get("name"));//执行结果：xinxin
        jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name"));
    }

    @Test
    public void testDel() {
        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
    }

    @Test
    public void testMap() {
        //-----添加数据----------
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user2", map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user2", "name", "age", "qq");
        System.out.println(rsmap);

        //删除map中的某个键值
        jedis.hdel("user2", "age");
        System.out.println(jedis.hmget("user2", "age")); //因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user2")); //返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user2"));//是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user2"));//返回map对象中的所有key
        System.out.println(jedis.hvals("user2"));//返回map对象中的所有value

        Iterator<String> iter = jedis.hkeys("user2").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user2", key));
        }
    }

    @Test
    public void testList() {
        //开始前，先移除所有的内容
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        //无序：
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        //再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework", 0, -1));
        //有序：
        jedis.del("java framework");
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");
        jedis.rpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));
    }

    @Test
    public void testSet() {
        //添加
        jedis.sadd("user1", "liuling");
        jedis.sadd("user1", "xinxin");
        jedis.sadd("user1", "ling");
        jedis.sadd("user1", "zhangxinxin");
        jedis.sadd("user1", "who");
        //移除noname
        jedis.srem("user1", "who");
        System.out.println(jedis.smembers("user1"));//获取所有加入的value
        System.out.println(jedis.sismember("user1", "who"));//判断 who 是否是user集合的元素
        System.out.println(jedis.srandmember("user1")); //随机元素
        System.out.println(jedis.scard("user1"));//返回集合的元素个数
    }


}
