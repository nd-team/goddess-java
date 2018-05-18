package com.bjike.goddess.task.enums;

/**
 * 执行状态
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ExecStatus {
    /**
     * 未执行
     */
    UN_EXEC(0),
    /**
     * 执行中
     */
    EXECUTING(1),
    /**
     * 未完成
     */
    UNFINISHED(3),
    /**
     * 完成
     */
    FINISH(2);

    ExecStatus(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
