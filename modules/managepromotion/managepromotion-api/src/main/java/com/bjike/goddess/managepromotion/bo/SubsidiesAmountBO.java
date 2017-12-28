package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-8-21.
 */
public class SubsidiesAmountBO extends BaseBO {
    private String grade;
    private String major;
    private Integer subsidiesAmount;
    private Integer totalAllowance;


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getSubsidiesAmount() {
        return subsidiesAmount;
    }

    public void setSubsidiesAmount(Integer subsidiesAmount) {
        this.subsidiesAmount = subsidiesAmount;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(Integer totalAllowance) {
        this.totalAllowance = totalAllowance;
    }
}
