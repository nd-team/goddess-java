package com.bjike.goddess.moneyside.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 权责分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:13 ]
 * @Description: [ 权责分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyside_accrualallot")
public class AccrualAllot extends BaseEntity {

    /**
     * 投资人
     */
    @Column(name = "investor", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '投资人'")
    private String investor;

    /**
     * 权限
     */
    @Column(name = "permissions", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '权限'")
    private String permissions;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}