package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "名称不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 成本
     */
    @NotNull(message = "成本不能为空",groups = {ADD.class,EDIT.class})
    private Double salary;

    /**
     * 项目信息Id
     */
    @NotBlank(message = "项目不能为空",groups = {ADD.class,EDIT.class})
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