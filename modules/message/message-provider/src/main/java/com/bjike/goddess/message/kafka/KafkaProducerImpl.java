package com.bjike.goddess.message.kafka;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.message.to.MessageTO;
import kafka.serializer.StringEncoder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
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
public class KafkaProducerImpl implements IKafkaProducer {
    @Autowired
    private Environment env;

    @Override
    public void produce(MessageTO messageTO) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,env.getProperty("metadata.broker.list"));
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);  props.put(ProducerConfig.ACKS_CONFIG, "-1");
        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord("messages", messageTO.getId(), JSON.toJSONString(messageTO)));
    }


}
