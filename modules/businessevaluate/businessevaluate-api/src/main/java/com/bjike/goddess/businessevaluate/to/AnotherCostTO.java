package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 其它成本
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 01:46 ]
 * @Description: [ 其它成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnotherCostTO extends BaseTO {

    /**
     * 名称
     */
    private String name;

    /**
     * 成本
     */
    private Double salary;

    /**
     * 项目信息Id
     */
    private String projectInfoId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}