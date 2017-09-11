package com.bjike.goddess.message.kafka;

import com.bjike.goddess.message.mail.MailSer;
import kafka.consumer.ConsumerConfig;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-15 11:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class KafkaConsumer {

    public static MailSer mailSer;
    public static Environment env;

    public void consumer() {
        Properties props = new Properties();
        //zookeeper 配置
        props.put("zookeeper.connect", env.getProperty("zookeeper.connect"));

        //group 代表一个消费组
        props.put("group.id", env.getProperty("group.id"));

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", env.getProperty("zookeeper.session.timeout.ms"));
        props.put("zookeeper.sync.time.ms", env.getProperty("zookeeper.sync.time.ms"));
        //自动提交偏移量时间
        props.put("auto.commit.interval.ms", env.getProperty("auto.commit.interval.ms"));
        props.put("auto.offset.reset", env.getProperty("auto.offset.reset"));
        //序列化类
        props.put("serializer.class", env.getProperty("serializer.class"));
        ConsumerConfig config = new ConsumerConfig(props);
        new Thread(new MsgRunner(mailSer,"messages",config)).start();
        new Thread(new MsgRunner(mailSer,"assn_pub_msg",config)).start();
    }


}
