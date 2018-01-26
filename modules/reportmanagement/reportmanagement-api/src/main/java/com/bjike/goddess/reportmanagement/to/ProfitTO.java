package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.reportmanagement.enums.ProfitType;
import com.bjike.goddess.reportmanagement.enums.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * 利润表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitTO extends BaseTO {

    /**
     * 项目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目不能为空")
    private String project;

//    /**
//     * 本月数
//     */
//    @NotNull(groups = {ADD.class, EDIT.class}, message = "本月数不能为空")
//    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "本月数不能小于0")
//    private Double currentMonthAmount;
//
//    /**
//     * 本年累计数
//     */
//    @NotNull(groups = {ADD.class, EDIT.class}, message = "本年累计数不能为空")
//    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, message = "本年累计数不能小于0")
//    private Double currentYearAmount;
    /**
     * 项目类型
     */
    private String projectType;
    /**
     * 利润类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "利润类型不能为空")
    private ProfitType profitType;

    /**
     * 运算类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "运算类型不能为空")
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ProfitType getProfitType() {
        return profitType;
    }

    public void setProfitType(ProfitType profitType) {
        this.profitType = profitType;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
}