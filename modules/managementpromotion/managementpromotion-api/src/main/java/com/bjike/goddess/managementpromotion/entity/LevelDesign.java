package com.bjike.goddess.managementpromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 管理分类等级设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:59 ]
 * @Description: [ 管理分类等级设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managementpromotion_leveldesign")
public class LevelDesign extends BaseEntity {

    /**
     * 分类
     */
    @Column(name = "classification", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '分类'")
    private String classification;

    /**
     * 管理方向
     */
    @Column(name = "direction", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '管理方向'")
    private String direction;

    /**
     * 技能等级
     */
    @Column(name = "skillLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能等级'")
    private String skillLevel;

    /**
     * 档次
     */
    @Column(name = "grade", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '档次'")
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