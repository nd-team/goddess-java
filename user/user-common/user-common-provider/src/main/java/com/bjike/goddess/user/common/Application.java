package com.bjike.goddess.user.common;

import com.bjike.goddess.user.common.boot.App;
import com.dounine.corgi.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户通用模块发布远程调用接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOGGER.info("user-common-provider starting...");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        ApplicationContext.setApplicationContext(context);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

