package com.bjike.goddess.enterpriseculturemanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.enterpriseculturemanage.action","com.bjike.goddess.enterpriseculturemanage.config","com.bjike.goddess.common.consumer"},
		excludeFilters = {@ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				value = {Configuration.class})})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
@ImportResource({"classpath:app.xml"})
public class Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class,args);
		System.in.read();
	}

}

