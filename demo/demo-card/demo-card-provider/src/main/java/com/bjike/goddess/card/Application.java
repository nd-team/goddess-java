package com.bjike.goddess.card;

import com.bjike.goddess.card.config.AppRoot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Application {
	
	static AnnotationConfigApplicationContext context = null;

    public static void main(String[] args) throws IOException {
        context = new AnnotationConfigApplicationContext(AppRoot.class);
        context.start();
        System.in.read(); // 按任意键退出
    }
}

