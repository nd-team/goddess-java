package com.bjike.goddess.regularization.type;

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
     * 申请转正中列表
     */
    ZZLIST(0),
    /**
     * 申请转正中添加
     */
    ZZADD(1),
    /**
     * 申请转正中编辑
     */
    ZZEDIT(2),
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
     * 管理层评分
     */
    MANAGSCORE(7),
    /**
     * 决策层评分
     */
    DECISIONSCORE(8),
    /**
     * 规划模块补充
     */
    PLANMODUL(9),
    /**
     * 预算模块补充
     */
    BUDGETMODUL(10),
    /**
     * 总经办审批
     */
    AUDIT(11);



    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
