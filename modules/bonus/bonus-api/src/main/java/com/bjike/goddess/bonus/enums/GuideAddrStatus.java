package com.bjike.goddess.bonus.enums;

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
     * 流程绩效指标列表
     */
    LIST(0),
    /**
     * 流程绩效指标添加
     */
    ADD(1),
    /**
     * 流程绩效指标编辑
     */
    EDIT(2),
    /**
     * 流程绩效指标删除
     */
    DELETE(3),
    /**
     * 奖励,处罚明细列表
     */
    JCLIST(4),
    /**
     * 奖励,处罚明细添加
     */
    JCADD(5),
    /**
     * 奖励,处罚明细编辑
     */
    JCEDIT(6),
    /**
     * 奖励,处罚明细删除
     */
    JCDELETE(7),
    /**
     * 开启
     */
    OPERS(8),
    /**
     * 关闭
     */
    CLOSES(9),
    /**
     * 汇总
     */
    SUMMARY(10),
    /**
     * 项目组排名
     */
    PROJECTRANK(11),
    /**
     * 个人排名
     */
    PERSONRANK(12);
    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
