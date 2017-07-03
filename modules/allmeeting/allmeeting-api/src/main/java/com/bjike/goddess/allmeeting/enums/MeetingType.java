package com.bjike.goddess.allmeeting.enums;

/**
 * 会议形式
 * <p>
 * Created by ike on 17-5-31.
 */
public enum MeetingType {

    /**
     * 线上
     */
    ONLINE(0),

    /**
     * 线下
     */
    OFFLINE(1);

    private int code;

    MeetingType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
