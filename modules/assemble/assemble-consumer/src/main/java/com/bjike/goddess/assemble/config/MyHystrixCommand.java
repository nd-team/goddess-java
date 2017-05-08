package com.bjike.goddess.assemble.config;

import com.bjike.goddess.common.consumer.aspect.HystrixCommandAdvice;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 默认5秒内失败20个,断路器会打开
 * Created by lake on 17-4-15.
 */
@Aspect
@Component
public class MyHystrixCommand extends HystrixCommandAdvice {}
