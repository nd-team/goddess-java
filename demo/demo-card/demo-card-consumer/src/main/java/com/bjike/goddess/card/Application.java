package com.bjike.goddess.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.card.action","com.bjike.goddess.common.consumer"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
@ImportResource("classpath:app.xml")
public class Application{
//
//	public static void initJapi(){
//		try {
//			FileUtil.UPLOAD_REQUEST = new HttpPost("http://localhost:8080/interfaceapidoc/upload");
//			ApplicationClient.startClient("demo-card-consumer","/home/ike/java/github/goddess-java/demo/demo-card/demo-card-consumer/src/main/java/com/bjike/goddess/card/action","com.bjike.goddess.card.action",Application.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}



	public static void main(String[] args) throws IOException {
//		initJapi();
		SpringApplication.run(Application.class,args);
		System.in.read();
	}

}

