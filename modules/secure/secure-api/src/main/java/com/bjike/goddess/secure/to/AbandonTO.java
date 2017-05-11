package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 放弃购买名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:52 ]
 * @Description: [ 放弃购买名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AbandonTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(groups = {EDIT.class},message = "姓名不能为空")
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(groups = {EDIT.class},message = "员工编号不能为空")
    private String employeeNum;

    /**
     * 项目组
     */
    @NotBlank(groups = {EDIT.class},message = "项目组不能为空")
    private String group1;

    /**
     * 放弃购买原因
     */
    @NotBlank(groups = {EDIT.class},message = "放弃购买原因不能为空")
    private String reason;

    /**
     * 是否签署协议
     */
    @NotNull(groups = {EDIT.class},message = "是否签署协议不能为空")
    private boolean sign;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getGroup() {
        return group1;
    }

    public void setGroup(String group1) {
        this.group1 = group1;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean getSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }
}