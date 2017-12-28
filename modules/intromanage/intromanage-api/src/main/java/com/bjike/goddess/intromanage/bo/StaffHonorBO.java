package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 员工荣誉业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:34 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffHonorBO extends BaseBO {

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 荣誉名称
     */
    private String honorName;

    /**
     * 荣誉等级
     */
    private String honorGrade;

    /**
     * 公司补助
     */
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