package com.bjike.goddess.user.utils;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.session.validcorrect.Subject;
import com.bjike.goddess.user.session.validcorrect.UserSession;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-20 10:33]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserUtil {

    public static User currentUser() throws SerException {
        Object token = RpcContext.getContext().getAttachment("userToken");
        if (null != token) {
            Subject subject = UserSession.get(token.toString());
            if (null != subject) {
                return subject.getUser();
            }
        }
        throw new SerException("登录已过期");
    }

    public static Boolean isLogin() throws SerException {
        Object token = RpcContext.getContext().getAttachment("userToken");
        if (null != token) {
            return null != UserSession.get(token.toString());
        }
        return false;

    }


}
