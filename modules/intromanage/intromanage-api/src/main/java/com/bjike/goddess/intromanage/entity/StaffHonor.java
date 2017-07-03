package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 员工荣誉
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:34 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_staffhonor")
public class StaffHonor extends BaseEntity {

    /**
     * 员工id
     */
    @Column(name = "staffId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '姓名'")
    private String staffId;

    /**
     * 荣誉名称
     */
    @Column(name = "honorName", columnDefinition = "VARCHAR(255) COMMENT '荣誉名称'")
    private String honorName;

    /**
     * 荣誉等级
     */
    @Column(name = "honorGrade", columnDefinition = "VARCHAR(255) COMMENT '荣誉等级'")
    private String honorGrade;

    /**
     * 公司补助
     */
    @Column(name = "firmSubsidy", columnDefinition = "VARCHAR(255) COMMENT '公司补助'")
    private String firmSubsidy;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public String getHonorGrade() {
        return honorGrade;
    }

    public void setHonorGrade(String honorGrade) {
        this.honorGrade = honorGrade;
    }

    public String getFirmSubsidy() {
        return firmSubsidy;
    }

    public void setFirmSubsidy(String firmSubsidy) {
        this.firmSubsidy = firmSubsidy;
    }
}