package com.bjike.goddess.common.consumer.config;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by lake on 17-4-15.
 */

public class HystrixCommandAdvice {
    private String groupName = "default";
    private String commandName = "default";

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

        return new HystrixCommand<Object>(setter()) {
            @Override
            protected Object run() throws Exception {
                try {
                    return pjp.proceed();
                } catch (Throwable throwable) {
                    if(throwable instanceof ActException){
                        throw new HystrixBadRequestException(throwable.getMessage());
                    }else{
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

    private HystrixCommand.Setter setter() {
        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupName));
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
