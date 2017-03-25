package com.bjike.goddess.headcount;

import com.bjike.goddess.headcount.config.AppRoot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @Author: [yewenbo]
 * @Date: [2017-2-20 15:47]
 * @Description: [用户通用模块发布远程调用接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Application {

    static AnnotationConfigApplicationContext context = null;

    public static void main(String[] args) throws IOException {
        context = new AnnotationConfigApplicationContext(AppRoot.class);
        context.start();
        System.in.read(); // 按任意键退出
    }
}

