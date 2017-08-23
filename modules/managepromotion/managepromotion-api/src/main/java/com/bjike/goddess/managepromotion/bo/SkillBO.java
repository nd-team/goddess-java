package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * Created by ike on 17-8-21.
 */
public class SkillBO extends BaseBO {
    private String major;
    private String grade;
    private List<LittleBO> littleBOS;

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

    public List<LittleBO> getLittleBOS() {
        return littleBOS;
    }

    public void setLittleBOS(List<LittleBO> littleBOS) {
        this.littleBOS = littleBOS;
    }
}
