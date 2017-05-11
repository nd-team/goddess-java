package com.bjike.goddess.assemble.type;

/**
 * Created by lake on 17-5-8.
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
