package com.bjike.goddess.user.utils;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.session.validcorrect.Subject;
import com.bjike.goddess.user.session.validcorrect.UserSession;

/**
 * 用户工具类
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-20 10:33]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserUtil {

    /**
     * 获取当前登录用户
     *
     * @return
     * @throws SerException
     */
    public static User currentUser() throws SerException {
        Object token = RpcContext.getContext().getAttachment("userToken");
        if (null != token) {
            Subject subject = UserSession.get(token.toString());
            if (null != subject) {
                return subject.getUser();
            }
            throw new SerException("登录已过期!");
        }
        throw new SerException("用户未登录!");
    }

    /**
     * 通过userToken获取当前登录用户
     *
     * @param userToken 用户令牌
     * @return
     * @throws SerException
     */
    public static User currentUser(Object userToken) throws SerException {
        if (null == userToken) {
            throw new SerException("用户未登录!");
        } else {
            Subject subject = UserSession.get(userToken.toString());
            if (null != subject) {
                return subject.getUser();
            }
            throw new SerException("登录已过期!");
        }

    }

    /**
     * 用户是否已登录
     *
     * @return
     * @throws SerException
     */
    public static Boolean isLogin() throws SerException {
        Object token = RpcContext.getContext().getAttachment("userToken");
        if (null != token) {
            return null != UserSession.get(token.toString());
        }
        return false;

    }


}
