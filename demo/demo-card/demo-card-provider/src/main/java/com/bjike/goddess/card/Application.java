package com.bjike.goddess.card;

import com.bjike.goddess.card.config.AppRoot;
import com.bjike.goddess.card.service.CardAPI;
import com.bjike.goddess.dbs.common.exception.SerException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Application {
	
	static AnnotationConfigApplicationContext context = null;

    public static void main(String[] args) throws IOException {
        context = new AnnotationConfigApplicationContext(AppRoot.class);
        context.start();
        CardAPI cardAPI = (CardAPI) context.getBean("cardSer");
        try {
            cardAPI.buyTicketForCard(null,"123","123","D3608-2-2D");
        } catch (SerException e) {
            e.printStackTrace();
        }
        System.in.read(); // 按任意键退出
    }
}

