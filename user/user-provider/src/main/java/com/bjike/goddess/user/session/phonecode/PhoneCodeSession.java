package com.bjike.goddess.user.session.phonecode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 09:28]
 * @Description: [手机验证码管理会话]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PhoneCodeSession {

    private static final Logger CONSOLE = LoggerFactory.getLogger(PhoneCodeSession.class);
    private static final Map<String, PhoneCode> PHONE_CODE_SESSIONS = new ConcurrentHashMap<>(0);
    private static final RuntimeException ACCOUNT_NOT_NULL = new RuntimeException("手机号码不能为空");

    private PhoneCodeSession() {
    }

    static {
        CONSOLE.info("PhoneCodeSession start");
        new PhoneCodeQuartz(PHONE_CODE_SESSIONS);
    }


    /**
     * 通过手机号码获取手机验证码
     *
     * @param phone 手机号码
     */
    public static PhoneCode get(String phone) {
        if (StringUtils.isNotBlank(phone)) {
            return  PHONE_CODE_SESSIONS.get(phone);
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }

    /**
     * 添加验证码
     *
     * @param phone 手机号
     */
    public static void put(String phone, PhoneCode phoneCode) {
        if (StringUtils.isNotBlank(phone)) {
            if(PHONE_CODE_SESSIONS.containsKey(phone)){
                PHONE_CODE_SESSIONS.remove(phone);
            }
            PHONE_CODE_SESSIONS.put(phone, phoneCode);
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }

    /**
     * 获取会话总数
     *
     * @return 总数
     */
    public static long count() {
        return PHONE_CODE_SESSIONS.size();
    }

    /**
     * 根据手机号码删除验证会话信息
     *
     * @param phone 手机号码
     */
    public static void remove(String phone) {
        if (StringUtils.isNotBlank(phone)) {
            PHONE_CODE_SESSIONS.remove(phone);
        }
        throw ACCOUNT_NOT_NULL;
    }


}
