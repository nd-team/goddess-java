package com.bjike.goddess.managepromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 技能定级A
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级A ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managepromotion_skillgradinga")
public class SkillGradingA extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 行业
     */
    @Column(name = "industry", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '行业'")
    private String industry;

    /**
     * 业务方向-科目
     */
    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向-科目'")
    private String subject;

    /**
     * 技能定位-专业（业务范围内包含的技能）
     */
    @Column(name = "major", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '技能定位-专业（业务范围内包含的技能）'")
    private String major;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}