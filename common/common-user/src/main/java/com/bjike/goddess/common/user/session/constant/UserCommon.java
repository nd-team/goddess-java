package com.bjike.goddess.common.user.session.constant;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-30 14:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserCommon {
    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "loginUser";

    /**
     * 登录用户TOKEN索引
     */
    public static final String USERID_TOKEN = "userIdToken";

    /**
     * 登录失效时间 1天
     */
    public static final Integer LOGIN_TIMEOUT = 60 * 24;

    /**
     * 验证码失效时间 5分钟
     */
    public static final Integer AUTH_CODE_TIMEOUT = 5;
    /**
     * 密码错误次数失效时间 5分钟
     */
    public static final Integer ERR_CODE_TIMEOUT = 5;
}
