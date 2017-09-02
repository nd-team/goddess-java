package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 计算
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 计算 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CalculateBO extends BaseBO {
    public CalculateBO(){}
    public CalculateBO(String main,String skill,String eventFirst,String eventFirstSkill,
                       String eventSecond,String eventSecondSkill,String eventThird,
                       String eventThirdSkill,String eventFour,String eventFourSkill,
                       String eventFive,String eventFiveSkill){
        this.main = main;
        this.skill = skill;
        this.eventFirst = eventFirst;
        this.eventFirstSkill = eventFirstSkill;
        this.eventSecond = eventSecond;
        this.eventSecondSkill = eventSecondSkill;
        this.eventThird = eventThird;
        this.eventThirdSkill = eventThirdSkill;
        this.eventFour = eventFour;
        this.eventFourSkill = eventFourSkill;
        this.eventFive = eventFive;
        this.eventFiveSkill = eventFiveSkill;
    }
    /**
     * 主项技能定位-专业
     */
    private Integer subsidiesAmount;

    /**
     * 主项技能等级
     */
    private Integer quotaJobTitle;
    /**
     * 主项技能定位-专业
     */
    private String main;

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

    public Integer getSubsidiesAmount() {
        return subsidiesAmount;
    }

    public void setSubsidiesAmount(Integer subsidiesAmount) {
        this.subsidiesAmount = subsidiesAmount;
    }

    public Integer getQuotaJobTitle() {
        return quotaJobTitle;
    }

    public void setQuotaJobTitle(Integer quotaJobTitle) {
        this.quotaJobTitle = quotaJobTitle;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
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
}