package com.bjike.goddess.message.kafka;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.message.to.MessageTO;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-24 16:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class KafkaProducerImpl implements KafkaProducer {
    @Autowired
    private Environment env;

    @Override
    public void produce(MessageTO messageTO) {

        Properties props = new Properties();
        //此处配置的是kafka的端口
        props.put("metadata.broker.list", env.getProperty("metadata.broker.list"));

        //配置value的序列化类
        props.put("serializer.class", env.getProperty("serializer.class"));
        //配置key的序列化类
        props.put("key.serializer.class", StringEncoder.class.getName());
        props.put("request.required.acks", env.getProperty("request.required.acks"));
        Producer producer = new Producer(new ProducerConfig(props));
        producer.send(new KeyedMessage("message", messageTO.getId(), JSON.toJSONString(messageTO)));
    }


}
