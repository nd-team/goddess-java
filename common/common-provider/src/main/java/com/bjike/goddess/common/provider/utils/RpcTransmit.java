package com.bjike.goddess.common.provider.utils;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.provider.common.RpcCommon;

/**
 * token 传递
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-18 14:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RpcTransmit {
    /**
     * 传递用户token
     */
    public static void transmitUserToken() {
        String token = RpcContext.getContext().getAttachment(RpcCommon.USER_TOKEN);
        RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
    }

    /**
     * 传递用户token
     */
    public static void transmitUserToken(String userToken) {
        RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, userToken);
    }



    /**
     * 获取用户token
     *
     * @return
     */
    public static String getUserToken() {
        return RpcContext.getContext().getAttachment(RpcCommon.USER_TOKEN);
    }



}
