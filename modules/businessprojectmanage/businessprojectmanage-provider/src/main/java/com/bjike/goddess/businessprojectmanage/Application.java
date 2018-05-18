package com.bjike.goddess.businessprojectmanage;

import com.bjike.goddess.businessprojectmanage.config.AppRoot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * 项目进度管理模块发布远程调用接口
 *
 * @Author: [caiwenxian]
 * @Date: [2017-12-09 15:47]
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

