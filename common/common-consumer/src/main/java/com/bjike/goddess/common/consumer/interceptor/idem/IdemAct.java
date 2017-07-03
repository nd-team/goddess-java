package com.bjike.goddess.common.consumer.interceptor.idem;

import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by lake on 17-4-17.
 */
@RestController
public class IdemAct {

    @Autowired(required = false)
    private IdempotencyInterceptor idempotencyFilter;

    @GetMapping("rtoken")
    public Result idem(HttpServletRequest request, HttpServletResponse response){
        if(null==idempotencyFilter){
            ActResult actResult = new ActResult("请联系管理员开启请求幂等功能",null);
            actResult.setCode(1);
            return actResult;
        }
        String uuid = UUID.randomUUID().toString();
        IdempotencyInterceptor.getLoadingCache().put(uuid,new Info());
        return new ActResult(null,uuid);
    }

}
