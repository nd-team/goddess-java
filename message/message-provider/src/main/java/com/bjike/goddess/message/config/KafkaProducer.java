package com.bjike.goddess.message.config;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.message.to.MessageTO;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class KafkaProducer {

    private static Producer producer;
    public final static String TOPIC = "TEST-TOPIC"; //消息标签

    @Bean
    public Producer  initProducer(Environment env) {
        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", env.getProperty("metadata.broker.list"));

        //配置value的序列化类
        props.put("serializer.class", env.getProperty("serializer.class"));
        //配置key的序列化类
        props.put("key.serializer.class", env.getProperty("key.serializer.class"));
        props.put("request.required.acks", env.getProperty("request.required.acks"));
        producer = new Producer(new ProducerConfig(props));
        return producer;
    }


    public static void produce(MessageTO messageTO) {
        producer.send(new KeyedMessage(messageTO.getId(), messageTO.getTitle(), JSON.toJSONString(messageTO)));
        if (null!=messageTO.getSendEmail()) {
            //sendEmail()
        }
        System.out.println(JSON.toJSONString(messageTO));
    }


}
