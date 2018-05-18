package com.bjike.goddess.managepromotion.enums;

/**
 * 是否通过
 *
 * @Author: [xiazhili]
 * @Date: [17-4-17]
 * @Description: [是否通过]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum PassStatus {

    /**
     * 模块负责人通过
     */
    HEADPASS(0),
    /**
     * 模块负责人未通过
     */
    HEADNOPASS(1),
    /**
     * 运营商务部预算模块通过
     */
    BUDGETPASS(2),
    /**
     * 运营商务部预算模块未通过
     */
    BUDGETNOPASS(3),
    /**
     * 项目经理通过
     */
    MANAGEPASS(4),
    /**
     * 项目经理未通过
     */
    MANAGENOPASS(5),
    /**
     * 综合资源部规划模块通过
     */
    PLANPASS(6),
    /**
     * 综合资源部规划模块未通过
     */
    PLANNOPASS(7),
    /**
     * 总经办通过
     */
    GENERALPASS(8),
    /**
     * 总经办未通过
     */
    GENERALNOPASS(9),;

    private int code;

    PassStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
