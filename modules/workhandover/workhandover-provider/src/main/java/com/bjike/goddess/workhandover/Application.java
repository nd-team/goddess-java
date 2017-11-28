package com.bjike.goddess.workhandover;

import com.bjike.goddess.workhandover.config.AppRoot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * 用户通用模块发布远程调用接口
 *
 * @Author: [chenyang]
 * @Date: [2017-11-23 15:47]
 * @Description: []
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

