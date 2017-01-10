package com.bjike.goddess.dbs.common.enums;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [简单的数据状态]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum Status {
    THAW(0)//解冻(正常)
    , CONGEAL(1)//冻结
    , DELETE(2)//删除
    , NOACTIVE(3)//未激活
    , UNREVIEW(4)//未审核
    ;

    private int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
