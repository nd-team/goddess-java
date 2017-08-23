package com.bjike.goddess.allmeeting.enums;

/**
 * 会议形式
 * <p>
 * Created by ike on 17-5-31.
 */
public enum AffectNode {

    /**
     * 执行
     */
    EXECUTE(0),

    /**
     * 审核
     */
    AUDIT(1),
    /**
     * 监督
     */
    SUPERVISE(2);

    private int code;

    AffectNode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
