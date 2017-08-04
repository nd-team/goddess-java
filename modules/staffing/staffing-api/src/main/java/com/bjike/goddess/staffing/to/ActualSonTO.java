package com.bjike.goddess.staffing.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 人数配置-实际子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-03 11:19 ]
 * @Description: [ 人数配置-实际子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ActualSonTO extends BaseTO {

    /**
     * 部门
     */
    private String depart;

    /**
     * 项目分类
     */
    private String project;

    /**
     * 决策层人数
     */
    private Integer policyNum;

    /**
     * 管理层人数
     */
    private Integer manNum;

    /**
     * 执行层人数
     */
    private Integer executeNum;

    /**
     * 调配人数
     */
    private Integer deployNum;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getPolicyNum() {
        return policyNum;
    }

    public void setPolicyNum(Integer policyNum) {
        this.policyNum = policyNum;
    }

    public Integer getManNum() {
        return manNum;
    }

    public void setManNum(Integer manNum) {
        this.manNum = manNum;
    }

    public Integer getExecuteNum() {
        return executeNum;
    }

    public void setExecuteNum(Integer executeNum) {
        this.executeNum = executeNum;
    }

    public Integer getDeployNum() {
        return deployNum;
    }

    public void setDeployNum(Integer deployNum) {
        this.deployNum = deployNum;
    }
}