package com.bjike.goddess.card;

import com.dounine.japi.JapiClient;
import com.dounine.japi.JapiClientStorage;
import com.dounine.japi.JapiClientTransfer;
import com.dounine.japi.core.IProject;
import com.dounine.japi.core.impl.ProjectImpl;
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

	public static void japi(){
		JapiClient.setProjectJavaPath("/home/lgq/github/goddess-java/demo/demo-card/demo-card-consumer/src/main/java");
		JapiClient.setActionReletivePath("com/bjike/goddess/card/action");
		JapiClient.setIncludeProjectJavaPath(new String[]{
				"/home/lgq/github/goddess-java/demo/demo-card/demo-card-api/src/main/java",
				"/home/lgq/github/goddess-java/common/common-consumer/src/main/java"
		});
        JapiClient.setIncludePackages(new String[]{"com.bjike.goddess"});
		JapiClient.setUseCache(false);//

		IProject project = ProjectImpl.init();
		JapiClientStorage japiClientStorage = JapiClientStorage.getInstance();
		japiClientStorage.setProject(project);
		japiClientStorage.autoSaveToDisk();
		new JapiClientTransfer().autoTransfer(japiClientStorage);
	}


	public static void main(String[] args) throws IOException {
		japi();
		SpringApplication.run(Application.class,args);
		System.in.read();
	}

}

