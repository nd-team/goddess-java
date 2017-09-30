package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 离职管理面谈
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:39 ]
 * @Description: [ 离职管理面谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InterviewTO extends BaseTO {

//    /**
//     * 地区
//     */
//    private String area;
//
//    /**
//     * 项目组/部门
//     */
//    private String department;
//
//    /**
//     * 岗位
//     */
//    private String position;
//
//    /**
//     * 岗位层级
//     */
//    private String positionLever;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

//    /**
//     * 申请离职离职时间
//     */
//    private String applyTime;
//
//    /**
//     * 正式离职时间
//     */
//    private String dismissTime;

    /**
     * 是否需项目经理信息离职面谈
     */
    @NotNull(message = "是否需项目经理信息离职面谈不能为空", groups = {ADD.class, EDIT.class})
    private Boolean managerInterview;

    /**
     * 是否需模块负责人离职面谈
     */
    @NotNull(message = "是否需模块负责人离职面谈不能为空", groups = {ADD.class, EDIT.class})
    private Boolean principalInterview;

    /**
     * 是否需福利模块离职面谈
     */
    @NotNull(message = "是否需福利模块离职面谈不能为空", groups = {ADD.class, EDIT.class})
    private Boolean welfareInterview;

    /**
     * 是否需要总经办离职面谈
     */
    @NotNull(message = "是否需要总经办离职面谈不能为空", groups = {ADD.class, EDIT.class})
    private Boolean officeInterview;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getManagerInterview() {
        return managerInterview;
    }

    public void setManagerInterview(Boolean managerInterview) {
        this.managerInterview = managerInterview;
    }

    public Boolean getPrincipalInterview() {
        return principalInterview;
    }

    public void setPrincipalInterview(Boolean principalInterview) {
        this.principalInterview = principalInterview;
    }

    public Boolean getWelfareInterview() {
        return welfareInterview;
    }

    public void setWelfareInterview(Boolean welfareInterview) {
        this.welfareInterview = welfareInterview;
    }

    public Boolean getOfficeInterview() {
        return officeInterview;
    }

    public void setOfficeInterview(Boolean officeInterview) {
        this.officeInterview = officeInterview;
    }
}