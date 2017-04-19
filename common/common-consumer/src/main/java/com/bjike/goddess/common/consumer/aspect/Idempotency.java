package com.bjike.goddess.common.consumer.aspect;

import com.bjike.goddess.common.consumer.http.RequestContext;
import com.bjike.goddess.common.consumer.interceptor.idem.IdempotencyInterceptor;
import com.bjike.goddess.common.consumer.interceptor.idem.Info;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by lake on 17-4-19.
 */
@Order(Integer.MIN_VALUE)
@Aspect
@Component
public class Idempotency {

    @Autowired(required = false)
    private IdempotencyInterceptor idempotencyFilter;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PatchMapping)"
    )
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();//获取参数值
        Object object = joinPoint.proceed(args);
        if(null!=idempotencyFilter){
            String rtoken = RequestContext.get().getHeader("rtoken");
            if(StringUtils.isNotBlank(rtoken)){
                Info info = IdempotencyInterceptor.getLoadingCache().get(rtoken);
                if(null==info.getResult()){
                    info.setResult(object);
                    info.setStatus(Info.Status.AFTER);
                }
            }
        }
        return object;
    }
}
