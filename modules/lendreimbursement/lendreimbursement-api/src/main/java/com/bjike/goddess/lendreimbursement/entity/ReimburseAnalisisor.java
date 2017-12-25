package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 报销分析人员表
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:51 ]
 * @Description: [ 报销分析人员表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_reimburseanalisisor")
public class ReimburseAnalisisor extends BaseEntity {

    /**
     * 分析人
     */
    @Column(name = "userName", columnDefinition = "VARCHAR(255)   COMMENT '分析人'")
    private String userName;

    /**
     * 员工编号
     */
    @Column(name = "empNum", columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String empNum;

    /**
     * 职位
     */
    @Column(name = "position", columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 审核人员部门
     */
    @Column(name = "department", columnDefinition = "VARCHAR(255)   COMMENT '审核人员部门'")
    private String department;


    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}