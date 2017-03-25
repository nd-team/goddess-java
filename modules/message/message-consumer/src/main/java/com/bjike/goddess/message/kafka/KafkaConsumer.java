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


    public static EmailAPI emailAPI;

    public void consumer() {
        Properties props = new Properties();
        //zookeeper 配置
        props.put("zookeeper.connect", "localhost:2181");

        //group 代表一个消费组
        props.put("group.id", "group1");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        //自动提交偏移量时间
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        //序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(props);

        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("message", new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get("message").get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            String msg = new String(it.next().message());
            MessageTO to = JSON.parseObject(msg, MessageTO.class);
            Email email = new Email(to.getTitle(), to.getContent());
            email.setReceiver(to.getReceivers());
            try {
                emailAPI.send(email);
                System.out.println("收到消息：" + msg);
            } catch (SerException e) {
                System.out.println(e.getMessage());
            }


        }

    }

}
