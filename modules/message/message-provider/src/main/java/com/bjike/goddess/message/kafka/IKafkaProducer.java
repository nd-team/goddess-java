package com.bjike.goddess.message.kafka;

import com.bjike.goddess.message.to.MessageTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-15 11:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IKafkaProducer {

    default void produce(MessageTO messageTO) {

    }

}
