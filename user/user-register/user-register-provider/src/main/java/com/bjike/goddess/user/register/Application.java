package com.bjike.goddess.user.register;

import com.dounine.corgi.context.ApplicationContext;
import com.bjike.goddess.user.register.boot.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户注册模块发布远程调用接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        context.start();
        System.in.read(); // 按任意键退出
    }
}

