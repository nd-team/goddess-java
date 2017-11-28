package com.bjike.goddess.task.enums;

/**
 * 汇总条件
 *
 * @Author: [chenjunhao]
 * @Date: [2017-11-17 09:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum CollectSuitation {
    /**
     * 等于
     */
    EQ(1),
    /**
     * 不为空
     */
    NOTNULL(2),
    /**
     * 为空
     */
    NULL(3);


    CollectSuitation(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    @Override
    public String toString() {
        String s="";
        switch (code){
            case 1:
                s="等于";
                break;
            case 2:
                s="不为空";
                break;
            case 3:
                s="为空";
                break;
        }
        return s;
    }
}
