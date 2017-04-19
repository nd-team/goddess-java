package com.bjike.goddess.common.provider.filter;

import com.alibaba.dubbo.rpc.*;

/**
 * Created by huanghuanlai on 2017/1/12.
 */
public class UserFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String userToken = invocation.getAttachment("userToken");
        //某些方法并不需要token，亦可访问调用
        Result result = invoker.invoke(invocation);
//        RpcContext.getContext().setAttachment("userToken",userToken);
        result.getAttachments().put("userToken", userToken);
        return result;
    }
}
