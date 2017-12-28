package com.bjike.goddess.buyticket.enums;

/**
 * 导航权限
 *
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum GuideAddrStatus {


    /**
     * 申请购买飞机票,火车票列表
     */
    APPLIST(0),
    /**
     * 申请购买飞机票,火车票添加
     */
    APPADD(1),
    /**
     * 申请购买飞机票,火车票编辑
     */
    APPEDIT(2),
    /**
     * 列表
     */
    LIST(3),
    /**
     * 添加
     */
    ADD(4),
    /**
     * 编辑
     */
    EDIT(5),
    /**
     * 删除
     */
    DELETE(6),
    /**
     * 规划模块审核
     */
    PLANAUDIT(7),
    /**
     * 福利模块审核
     */
    WELFAUDIT(8),
    /**
     * 冻结
     */
    CONGEL(9),
    /**
     * 解冻
     */
    THAW(10),
    /**
     * 车票购买记录列表
     */
    RECORDLIST(11);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
