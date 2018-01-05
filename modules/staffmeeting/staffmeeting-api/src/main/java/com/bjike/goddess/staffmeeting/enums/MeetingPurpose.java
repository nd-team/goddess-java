package com.bjike.goddess.staffmeeting.enums;

/**
 * 议题目的
 * <p>
 * Created by ike on 17-5-31.
 */
public enum MeetingPurpose {

    /**
     * 监督
     */
    SUPERVISE(0),

    /**
     * 表决
     */
    VOTE(1);

    private int code;

    MeetingPurpose(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
