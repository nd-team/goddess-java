package com.bjike.goddess.common.user.session.valid_err;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-12 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PwdErrSession {

    private static final Map<String, PwdErr> PWD_ERR_SESSION = new ConcurrentHashMap<>(0);
    private static final RuntimeException ACCOUNT_NOT_NULL = new RuntimeException("account令牌不能为空");

    private PwdErrSession() {
    }

    static {
        new PwdErrQuartz(PWD_ERR_SESSION);
        System.out.println("pwd_err_session start");
    }

    /**
     * 新增密码错误会话信息
     *
     * @param account 用户名,手机,昵称
     */
    public static void put(String account) {
        if (null != account && !"".equals(account.trim())) {
            PwdErr pwdErr = get(account);
            if (null != pwdErr) {
                pwdErr.setAccessTime(LocalDateTime.now());
                pwdErr.setCount(pwdErr.getCount() + 1);
            } else {
                pwdErr = new PwdErr();
                pwdErr.setAccessTime(LocalDateTime.now());
                pwdErr.setCount(1);
            }
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }

    /**
     * 根据令牌删除错误会话信息
     *
     * @param account 用户名,手机,昵称
     */
    public static void remove(String account) {
        if (null != account && !"".equals(account.trim())) {
            PWD_ERR_SESSION.remove(account);
        } else {

            throw ACCOUNT_NOT_NULL;
        }
    }


    public static PwdErr get(String account) {
        if (null != account && !"".equals(account.trim())) {
            return PWD_ERR_SESSION.get(account);
        }
        throw ACCOUNT_NOT_NULL;
    }


}
