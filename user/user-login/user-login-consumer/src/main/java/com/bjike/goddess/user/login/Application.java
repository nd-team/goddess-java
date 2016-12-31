package com.bjike.goddess.user.login;

import com.bjike.goddess.user.login.config.exception.MyHanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@PropertySource("classpath:corgi.properties")
@ComponentScan(basePackages = "com.bjike.goddess.user.login",
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class Application extends WebMvcConfigurerAdapter{

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args){
        LOGGER.info("user-login-consumer starting...");
		SpringApplication.run(Application.class,args);
	}

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new MyHanException());//unified exception handling
    }

}

