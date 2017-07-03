package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.assistance.enums.EmpRight;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 权限设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:14 ]
 * @Description: [ 权限设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_rightset")
public class RightSet extends BaseEntity {

    /**
     * 员工名称
     */
    @Column(name = "empName", columnDefinition = "VARCHAR(255)   COMMENT '员工名称'")
    private String empName;

    /**
     * 权限
     */
    @Column(name = "empRight", columnDefinition = "INT(2)   COMMENT '权限'")
    private EmpRight empRight;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

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
}