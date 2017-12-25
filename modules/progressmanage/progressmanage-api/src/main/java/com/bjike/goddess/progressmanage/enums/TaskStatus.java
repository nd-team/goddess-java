package com.bjike.goddess.progressmanage.enums;

/**
 * 任务状态
 * <p>
 * Created by ike on 17-6-9.
 */
public enum TaskStatus {
    /**
     * 未分配
     */
    NOTALLOT(0),
    /**
     * 待确认接收
     */
    WAITAFFIRM(1),
    /**
     * 已确认接收
     */
    AFFIRMED(2),
    /**
     * 日期
     */
    NOTACCEPT(3),
    /**
     * 进行中
     */
    PROCEED(4),
    /**
     * 待确认完成
     */
    WAITFINISH(5),
    /**
     * 已完成
     */
    FINISHED(6),
    /**
     * 上报未完成
     */
    REPORTUNFINISH(7),
    /**
     * 未上报未完成
     */
    UNREPORTFINISH(8);

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    TaskStatus(int code) {
        this.code = code;
    }

}
