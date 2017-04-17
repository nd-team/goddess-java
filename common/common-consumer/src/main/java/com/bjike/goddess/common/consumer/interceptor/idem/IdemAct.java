package com.bjike.goddess.common.consumer.interceptor.idem;

import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.IdempotencyRequestInterceptor;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.apache.commons.lang3.StringUtils;
import org.mengyun.tcctransaction.api.UuidUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by lake on 17-4-17.
 */
@RestController
public class IdemAct {

    @GetMapping("rtoken")
    public Result idem(HttpServletRequest request, HttpServletResponse response){
        String uuid = UUID.randomUUID().toString();
        IdempotencyRequestInterceptor.getLoadingCache().put(uuid,new Info());
        return new ActResult(null,uuid);
    }

}
