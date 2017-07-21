package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 技能定级A
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级A ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillGradingABO extends BaseBO{

    /**
     * 体系
     */
    private String system;

    /**
     * 行业
     */
    private String industry;

    /**
     * 业务方向-科目
     */
    private String subject;

    /**
     * 技能定位-专业（业务范围内包含的技能）
     */
    private String major;
    /**
     * 技能定级B
     */
    private List<SkillGradingBBO> skillGradingBBOS;

    public List<SkillGradingBBO> getSkillGradingBBOS() {
        return skillGradingBBOS;
    }

    public void setSkillGradingBBOS(List<SkillGradingBBO> skillGradingBBOS) {
        this.skillGradingBBOS = skillGradingBBOS;
    }

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