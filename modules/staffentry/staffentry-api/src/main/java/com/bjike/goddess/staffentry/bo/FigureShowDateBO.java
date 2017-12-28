package com.bjike.goddess.staffentry.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 入职管理汇总业务传输对象
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 11:09]
 * @Description: [入职管理汇总业务传输对象]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FigureShowDateBO extends BaseBO {
    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 计划入职人数
     */
    private Integer planInductionNum;

    /**
     * 注册用户数
     */
    private Integer registrationNum;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getPlanInductionNum() {
        return planInductionNum;
    }

    public void setPlanInductionNum(Integer planInductionNum) {
        this.planInductionNum = planInductionNum;
    }

    public Integer getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(Integer registrationNum) {
        this.registrationNum = registrationNum;
    }
}
