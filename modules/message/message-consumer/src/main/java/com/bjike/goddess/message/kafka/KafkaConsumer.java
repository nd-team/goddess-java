package com.bjike.goddess.message.kafka;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.message.api.EmailAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.message.to.email.Email;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-15 11:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    public static EmailAPI emailAPI;
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

        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("messages", new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get("messages").get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            String msg = new String(it.next().message());
            try {
                MessageTO to = JSON.parseObject(msg, MessageTO.class);
                if (null != to.getReceivers()) {
                    try {
                        Email email = new Email(to.getTitle(), to.getContent());
                        email.setReceiver(to.getReceivers());
                        emailAPI.send(email);
                        LOGGER.info("收到消息:" + msg);
                    } catch (SerException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            } catch (Exception e) {
                LOGGER.error("消息json转换错误!");
            }

        }

    }

}
