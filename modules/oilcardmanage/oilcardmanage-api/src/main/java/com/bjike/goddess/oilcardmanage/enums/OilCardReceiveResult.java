package com.bjike.goddess.oilcardmanage.enums;

/**
 * 油卡领用审核结果
 *
 * @Author: [Jason]
 * @Date: [17-3-14 下午3:05]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum OilCardReceiveResult {

    /**
     * 待处理
     */
    PENDING(0),
    /**
     * 拒绝
     */
    REFUSE(1),
    /**
     * 同意
     */
    AGREE(2);

    private int code;

    OilCardReceiveResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
