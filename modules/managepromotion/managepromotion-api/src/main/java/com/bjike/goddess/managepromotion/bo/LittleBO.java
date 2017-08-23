package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-8-21.
 */
public class LittleBO extends BaseBO {
    private String major;
    private String grade;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
