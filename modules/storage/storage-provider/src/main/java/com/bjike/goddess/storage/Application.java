package com.bjike.goddess.storage;

import com.bjike.goddess.storage.config.AppRoot;
import com.caucho.hessian.client.HessianProxyFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * 用户通用模块发布远程调用接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Application {


    static AnnotationConfigApplicationContext context = null;

    public static void main(String[] args) throws IOException {
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);
        context = new AnnotationConfigApplicationContext(AppRoot.class);
        context.start();
        System.in.read(); // 按任意键退出
    }
}

