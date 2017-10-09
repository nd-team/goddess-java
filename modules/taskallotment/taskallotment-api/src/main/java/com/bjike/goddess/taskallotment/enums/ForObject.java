package com.bjike.goddess.taskallotment.enums;

/**
 * 用于对象
 * @Author: [chenjunhao]
 * @Date: [2017-09-15 11:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ForObject {
    /**
     * 全体
     */
    ALL(0),
    /**
     * 部门
     */
    DEPART(1),
    /**
     * 个人
     */
    PERSON(2);

    private int code;

    ForObject(int code) {
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
                s="全体";
                break;
            case 1:
                s="部门";
                break;
            case 2:
                s="个人";
                break;
        }
        return s;
    }
}
