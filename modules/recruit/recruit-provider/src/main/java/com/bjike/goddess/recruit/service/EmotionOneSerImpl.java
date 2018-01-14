package com.bjike.goddess.recruit.service;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.EmotionOneDTO;
import com.bjike.goddess.recruit.entity.EmotionOne;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 情感标签二级业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:57 ]
 * @Description: [ 情感标签二级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class EmotionOneSerImpl extends ServiceImpl<EmotionOne, EmotionOneDTO> implements EmotionOneSer {


    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        System.out.println("hh");

    }


    public void test() {

    }

}