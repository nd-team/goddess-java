package com.bjike.goddess.annual.enums;

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
     * 年假信息记录
     */
    XXLIST(0),
    /**
     * 年假信息添加
     */
    XXADD(1),
    /**
     * 年假信息申请
     */
    XXAPPLY(2),
    /**
     * 查看年假申请记录
     */
    XXSEE(3),
    /**
     * 我的年假记录
     */
    XXMYSELF(4),
    /**
     * 列表
     */
    LIST(5),
    /**
     * 添加
     */
    ADD(6),
    /**
     * 编辑
     */
    EDIT(7),
    /**
     * 删除
     */
    DELETE(8),
    /**
     * 查看
     */
    SEE(9),
    /**
     * 项目经理审核
     */
    AUDIT(10),
    /**
     * 冻结
     */
    CONGEL(11),
    /**
     * 解冻
     */
    THAW(12);


    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
