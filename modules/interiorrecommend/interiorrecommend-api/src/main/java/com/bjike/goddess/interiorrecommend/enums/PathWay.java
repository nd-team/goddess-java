package com.bjike.goddess.interiorrecommend.enums;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-12 16:52]
 * @Description: [推荐的途径]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PathWay {
    /**
     * 邮件
     */
    EMAIL(0),

    /**
     * 电话
     */
    PHONE(1),

    /**
     * QQ
     */
    QQ(2),

    /**
     * 微信
     */
    WECHAT(3),

    /**
     * 系统推荐
     */
    SYSTEMRE(4);

    private int code;

    PathWay(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
