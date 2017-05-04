package com.bjike.goddess.projectissuehandle.enums;

/**
 * 问题相关部门枚举
 *
 * @Author: [xiazhili]
 * @Date: [17-4-25]
 * @Description: [问题相关部门枚举]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
public enum ProblemRelevantDepartment {
    /**
     * 财务部门-预算
     */
    FINANCIALDEPARTMENTBUDGET(0),
    /**
     * 财务部门-账务
     */
    FINANCIALDEPARTMENTACCOUNT(1),
    /**
     * 财务部门-资金
     */
    FINANCIALDEPARTMENTMONEY(2),
    /**
     * 综合资源部-福利
     */
    COMPREHENSIVERESOURCESWELFARE(3),
    /**
     * 综合资源部-素养
     */
    COMPREHENSIVERESOURCESLITERACY(4),
    /**
     * 综合资源部-规划
     */
    COMPREHENSIVERESOURCESPLAN(5),
    /**
     * 一线实施体系-项目组
     */
    IMPLEMENTATIONSYSTEMPROJECTGROUP(6),
    /**
     * 一线实施体系-研发部
     */
    IMPLEMENTATIONSYSTEMDEVELOPDEPARTMENT(7),
    /**
     * 商务发展部-客户管理
     */
    BUSINESSDEVELOPCUSTOMER(8),
    /**
     * 商务发展部-业务管理
     */
    BUSINESSDEVELOPBUSINESS(9),
    /**
     * 商务发展部-进度管理
     */
    BUSINESSDEVELOPPROGRESS(10),
    ;

    private int code;

    ProblemRelevantDepartment(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
