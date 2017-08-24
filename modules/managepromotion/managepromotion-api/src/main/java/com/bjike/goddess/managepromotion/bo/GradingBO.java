package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.managepromotion.entity.SkillGradingA;
import com.bjike.goddess.managepromotion.entity.SkillGradingB;
import com.bjike.goddess.managepromotion.entity.SkillGradingC;

import java.util.List;

/**
 * Created by ike on 17-8-23.
 */
public class GradingBO extends BaseBO{
    /**
     * 主项技能定位-专业
     */
    private String major;

    /**
     * 主项技能等级
     */
    private String skill;
    /**
     * 小项1 技能定位-专业
     */
    private String eventFirst;

    /**
     * 小项技能定位-专业1技能等级
     */
    private String eventFirstSkill;

    /**
     * 小项2 技能定位-专业
     */
    private String eventSecond;
    /**
     * 小项技能定位-专业2技能等级
     */
    private String eventSecondSkill;
    /**
     * 小项3 技能定位-专业
     */
    private String eventThird;
    /**
     * 小项技能定位-专业3技能等级
     */
    private String eventThirdSkill;
    /**
     * 小项4 技能定位-专业
     */
    private String eventFour;
    /**
     * 小项技能定位-专业4技能等级
     */
    private String eventFourSkill;
    /**
     * 小项5 技能定位-专业
     */
    private String eventFive;
    /**
     * 小项技能定位-专业5技能等级
     */
    private String eventFiveSkill;
    private List<SkillGradingA> skillGradingAS;
    private List<SkillGradingB> skillGradingBS;
    private List<SkillGradingC> skillGradingCS;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEventFirst() {
        return eventFirst;
    }

    public void setEventFirst(String eventFirst) {
        this.eventFirst = eventFirst;
    }

    public String getEventFirstSkill() {
        return eventFirstSkill;
    }

    public void setEventFirstSkill(String eventFirstSkill) {
        this.eventFirstSkill = eventFirstSkill;
    }

    public String getEventSecond() {
        return eventSecond;
    }

    public void setEventSecond(String eventSecond) {
        this.eventSecond = eventSecond;
    }

    public String getEventSecondSkill() {
        return eventSecondSkill;
    }

    public void setEventSecondSkill(String eventSecondSkill) {
        this.eventSecondSkill = eventSecondSkill;
    }

    public String getEventThird() {
        return eventThird;
    }

    public void setEventThird(String eventThird) {
        this.eventThird = eventThird;
    }

    public String getEventThirdSkill() {
        return eventThirdSkill;
    }

    public void setEventThirdSkill(String eventThirdSkill) {
        this.eventThirdSkill = eventThirdSkill;
    }

    public String getEventFour() {
        return eventFour;
    }

    public void setEventFour(String eventFour) {
        this.eventFour = eventFour;
    }

    public String getEventFourSkill() {
        return eventFourSkill;
    }

    public void setEventFourSkill(String eventFourSkill) {
        this.eventFourSkill = eventFourSkill;
    }

    public String getEventFive() {
        return eventFive;
    }

    public void setEventFive(String eventFive) {
        this.eventFive = eventFive;
    }

    public String getEventFiveSkill() {
        return eventFiveSkill;
    }

    public void setEventFiveSkill(String eventFiveSkill) {
        this.eventFiveSkill = eventFiveSkill;
    }

    public List<SkillGradingA> getSkillGradingAS() {
        return skillGradingAS;
    }

    public void setSkillGradingAS(List<SkillGradingA> skillGradingAS) {
        this.skillGradingAS = skillGradingAS;
    }

    public List<SkillGradingB> getSkillGradingBS() {
        return skillGradingBS;
    }

    public void setSkillGradingBS(List<SkillGradingB> skillGradingBS) {
        this.skillGradingBS = skillGradingBS;
    }

    public List<SkillGradingC> getSkillGradingCS() {
        return skillGradingCS;
    }

    public void setSkillGradingCS(List<SkillGradingC> skillGradingCS) {
        this.skillGradingCS = skillGradingCS;
    }
}
