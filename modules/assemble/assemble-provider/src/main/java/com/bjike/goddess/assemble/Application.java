package com.bjike.goddess.assemble;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bjike.goddess.assemble.config.AppRoot;

import java.io.IOException;

public class Application {
	
	static AnnotationConfigApplicationContext context = null;

    public static void main(String[] args) throws IOException {
        context = new AnnotationConfigApplicationContext(AppRoot.class);
        context.start();
        System.in.read(); // 按任意键退出
    }
}

