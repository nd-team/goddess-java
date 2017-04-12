package com.bjike.goddess.quartz;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.bjike.goddess.quartz.config.AppRoot;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * 任务调度模块发布远程调用接口
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
        context = new AnnotationConfigApplicationContext(AppRoot.class);
        context.start();
        System.in.read(); // 按任意键退出
    }


}

