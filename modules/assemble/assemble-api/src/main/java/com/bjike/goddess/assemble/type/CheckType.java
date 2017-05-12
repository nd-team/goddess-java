package com.bjike.goddess.assemble.type;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-03 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  CheckType {
    /**
     * 选中
     */
    CHECK(0),
    /**
     * 未选中
     */
    NONE(1);

    private int value;

    CheckType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
