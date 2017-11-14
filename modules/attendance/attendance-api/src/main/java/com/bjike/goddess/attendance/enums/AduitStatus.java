package com.bjike.goddess.attendance.enums;

/**
 * 审核状态
 * @Author: [chenjunhao]
 * @Date: [2017-10-07 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum AduitStatus {
    /**
     * 审核中
     */
    DOING(0),
    /**
     * 通过
     */
    AGREE(1),
    /**
     * 不通过
     */
    REJECT(2);
    private int code;

    AduitStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    @Override
    public String toString() {
        String s="";
        switch (code){
            case 0:
                s="未处理";
                break;
            case 1:
                s="通过";
                break;
            case 2:
                s="不通过";
                break;
        }
        return s;
    }

    
}
