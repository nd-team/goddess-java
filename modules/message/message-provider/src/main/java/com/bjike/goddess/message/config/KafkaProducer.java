package com.bjike.goddess.message.config;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.message.to.MessageTO;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-15 11:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class KafkaProducer {

    public final static  String TOPIC ="test";

    public static void produce(MessageTO messageTO) {

      //  producer.send(new KeyedMessage(messageTO.getId(), messageTO.getTitle(), JSON.toJSONString(messageTO)));

    }
    public static void main(String[] args) {

        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", "localhost:9092");

        //配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
        props.put("key.serializer.class", StringEncoder.class.getName());
        props.put("request.required.acks", "-1");
        Producer  producer = new Producer(new ProducerConfig(props));
         producer.send(new KeyedMessage(TOPIC, "消息1", "这是消息内容111"));

    }


}
