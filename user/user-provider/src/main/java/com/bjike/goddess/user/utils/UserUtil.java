package com.bjike.goddess.user.utils;

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

    public static User currentUser(String token)throws SerException {
        Subject subject = UserSession.get(token);
        if (null != subject){
            return subject.getUser();
        }
         throw new  SerException("登录已过期");
    }
    public static Boolean isLogin(String token) throws SerException {
        Subject subject = UserSession.get(token);
        return null!=subject;
    }



}
