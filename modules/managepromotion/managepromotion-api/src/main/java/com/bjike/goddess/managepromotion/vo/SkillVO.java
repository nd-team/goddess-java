package com.bjike.goddess.managepromotion.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * Created by ike on 17-8-21.
 */
public class SkillVO extends BaseBO {
    private String major;
    private String grade;
    private List<LittleVO> littleBOS;

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

    public List<LittleVO> getLittleBOS() {
        return littleBOS;
    }

    public void setLittleBOS(List<LittleVO> littleBOS) {
        this.littleBOS = littleBOS;
    }
}
