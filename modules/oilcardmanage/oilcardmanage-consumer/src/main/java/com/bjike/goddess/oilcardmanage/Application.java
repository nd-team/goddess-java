package com.bjike.goddess.oilcardmanage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.oilcardmanage.action","com.bjike.goddess.oilcardmanage.config","com.bjike.goddess.common.consumer"},
		excludeFilters = {@ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				value = {Configuration.class})})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@ImportResource({"classpath:app.xml"})
public class Application {


	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("800MB");
		factory.setMaxRequestSize("800MB");
		return factory.createMultipartConfig();
	}


	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class,args);
		System.in.read();
	}

}

