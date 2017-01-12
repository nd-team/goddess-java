package com.bjike.goddess.card.service;

import com.alibaba.dubbo.rpc.*;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 2017/1/11.
 */
public class LoginCheck implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println(invocation.getAttachment("loginToken"));//获取隐式参数
        return invoker.invoke(invocation);
    }

}
