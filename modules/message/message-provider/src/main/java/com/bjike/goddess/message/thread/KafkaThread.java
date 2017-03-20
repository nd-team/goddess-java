package com.bjike.goddess.message.thread;

import kafka.javaapi.consumer.ConsumerConnector;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-17 14:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class KafkaThread implements Runnable {
    public boolean running = true;
    private ConsumerConnector consumer = null;

    public KafkaThread(ConsumerConnector consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(5 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            running = false;
            consumer.commitOffsets();
            consumer.shutdown();
        }
    }
}
