package com.bjike.goddess.rentcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bjike.goddess.rentcar.action","com.bjike.goddess.rentcar.config","com.bjike.goddess.common.consumer"},
		excludeFilters = {@ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				value = {Configuration.class})})
@ImportResource({"classpath:app.xml"})
@PropertySource(value = {"classpath:permission.properties"},encoding="utf-8")
public class Application {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class,args);
		System.in.read();
	}

}

