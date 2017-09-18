package com.bjike.goddess.message.kafka;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.message.mail.MailSer;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-11 14:28]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MsgRunner implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MsgRunner.class);
    private MailSer mailSer;
    private String topic;
    private ConsumerConfig config;

    public MsgRunner(MailSer mailSer, String topic, ConsumerConfig config) {
        this.mailSer = mailSer;
        this.topic = topic;
        this.config = config;
    }

    @Override
    public void run() {
        read(config, topic);
    }

    public void read(ConsumerConfig config, String topic) {

        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            String msg = new String(it.next().message());
            MessageTO to = null;
            try {
                to = JSON.parseObject(msg, MessageTO.class);

            } catch (Exception e) {
                LOGGER.error("消息json转换错误!");
            }
            if (null != to.getReceivers()) {
                try {
                    Email email = new Email(to.getTitle(), to.getContent());
                    email.setReceiver(to.getReceivers());
                    mailSer.send(email);
                    LOGGER.info("收到消息:" + msg);
                } catch (SerException e) {
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());
                }
            }
        }

    }

    public MailSer getMailSer() {
        return mailSer;
    }

    public void setMailSer(MailSer mailSer) {
        this.mailSer = mailSer;
    }
}
