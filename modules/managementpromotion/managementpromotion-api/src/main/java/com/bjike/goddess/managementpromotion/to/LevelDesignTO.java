package com.bjike.goddess.managementpromotion.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 管理分类等级设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:59 ]
 * @Description: [ 管理分类等级设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LevelDesignTO extends BaseTO {

    /**
     * 分类
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "体系不能为空")
    private String classification;

    /**
     * 管理方向
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "管理方向不能为空")
    private String direction;

    /**
     * 技能等级
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "技能等级不能为空")
    private String skillLevel;

    /**
     * 档次
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "档次不能为空")
    private String grade;


    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}