package com.bjike.goddess.common.consumer.aspect;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;


/**
 * Created by lake on 17-4-15.
 */

public class HystrixCommandAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PatchMapping)"
    )
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object runCommand(final ProceedingJoinPoint pjp) {
        return wrapWithHystrixCommnad(pjp).execute();
    }

    private HystrixCommand<Object> wrapWithHystrixCommnad(final ProceedingJoinPoint pjp) {

        return new HystrixCommand<Object>(setter(pjp)) {
            @Override
            protected Object run() throws Exception {
                try {
                    return pjp.proceed();
                } catch (Throwable throwable) {
                    if(throwable instanceof ActException){
                        throw new HystrixBadRequestException(throwable.getMessage(),throwable);
                    }else{
                        throwable.printStackTrace();
                        throw (Exception) throwable;
                    }
                }
            }

            @Override
            protected Object getFallback() {
                return new ActResult("服务不可用");
            }
        };
    }

    private HystrixCommand.Setter setter(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        HystrixCommand.Setter setter = null;
        String groupName = signature.getDeclaringTypeName(),commandKey= "";
        int timeOut = 10000;
        if(signature.getDeclaringType().isAnnotationPresent(DefaultProperties.class)){
            DefaultProperties defaultProperties = (DefaultProperties)signature.getDeclaringType().getDeclaredAnnotation(DefaultProperties.class);
            if(StringUtils.isNotBlank(defaultProperties.groupKey())){
                groupName = defaultProperties.groupKey();
            }
            defaultProperties.commandProperties();
            MethodSignature methodSignature = (MethodSignature) joinPoint
                    .getSignature();
            Method method = methodSignature.getMethod();
            com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand hystrixCommand  = method.getDeclaredAnnotation(com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand.class);
            if(null!=hystrixCommand){
                if(StringUtils.isNotBlank(hystrixCommand.groupKey())){
                    groupName =  hystrixCommand.groupKey();
                }
                if(StringUtils.isNotBlank(hystrixCommand.commandKey())){
                    commandKey = groupName+"/"+hystrixCommand.commandKey();
                }
            }
            setter = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("group/"+groupName)).andCommandKey(HystrixCommandKey.Factory.asKey("key/"+(StringUtils.isBlank(commandKey)?groupName:commandKey)));
            HystrixProperty[] hyps = defaultProperties.commandProperties();

            if(null!=hyps&&hyps.length>0){
                Optional<HystrixProperty> hystrixProperty = Arrays.asList(hyps).stream().filter(h->h.name().equals("execution.isolation.thread.timeoutInMilliseconds")).findFirst();
                if(hystrixProperty.isPresent()){
                    timeOut = Integer.parseInt(hystrixProperty.get().value());
                }
            }
            setter.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeOut));
        }else{
            setter = HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("group/default"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeOut))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("key/"+(StringUtils.isBlank(commandKey)?groupName:commandKey)));
        }
        return setter;
    }

}
