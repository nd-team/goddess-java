package com.bjike.goddess.common.consumer.interceptor.idem;

import com.bjike.goddess.common.consumer.interceptor.idem.IdempotencyInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lake on 17-4-19.
 */
//@Component
public class MyIdempotencyInterceptor extends IdempotencyInterceptor {


    @Override
    public String[] getExcludePathPatterns() {
        return super.getExcludePathPatterns();
    }

    @Override
    public String[] getPathPatterns() {
        return super.getPathPatterns();
    }
}
