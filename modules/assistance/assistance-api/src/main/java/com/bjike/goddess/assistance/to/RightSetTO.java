package com.bjike.goddess.assistance.to;

import com.bjike.goddess.assistance.enums.EmpRight;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 权限设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:14 ]
 * @Description: [ 权限设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RightSetTO extends BaseTO {

    /**
     * 员工名称
     */
    @NotBlank(message = "员工名称不能为空")
    private String empName;

    /**
     * 权限
     */
    @NotNull(message = "权限不能为空")
    private EmpRight empRight;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public EmpRight getEmpRight() {
        return empRight;
    }

    public void setEmpRight(EmpRight empRight) {
        this.empRight = empRight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}