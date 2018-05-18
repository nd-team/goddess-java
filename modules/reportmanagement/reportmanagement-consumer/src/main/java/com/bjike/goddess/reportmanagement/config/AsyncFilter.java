package com.bjike.goddess.reportmanagement.config;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-04-11 10:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
@Activate(group = {Constants.PROVIDER})
public class AsyncFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().getAttachments().remove(Constants.ASYNC_KEY);
        return invoker.invoke(invocation);
    }
}