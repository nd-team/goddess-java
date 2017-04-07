package com.bjike.goddess.enterpriseculturemanage.enums;

/**
 * 宣传形式
 *
 * @Author: [Jason]
 * @Date: [17-3-31 下午5:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum PublicizeWay {

    /**
     * 挂图、壁画
     */
    MURAl(0),
    /**
     * 员工活动
     */
    ACTIVITY(1),
    /**
     * 奖励评选
     */
    REWARD(2);

    private int code;

    PublicizeWay(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
