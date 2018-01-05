package com.bjike.goddess.democraticmeet.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 会议纪要
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 会议纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryEmail extends BaseBO {

    /**
     * 会议类型
     */
    private String typeName;
    /**
     * 会议议题
     */
    private String title;
    /**
     * 会议层面
     */
    private String titleLevel;

    /**
     * 会议编号
     */
    private String meetNumber;


    /**
     * 实际会议时间
     */
    private String actualTime;

    /**
     * 会议形式
     */
    private String meetForm;

    /**
     * 会议地点
     */
    private String area;

    /**
     * 计划参会人员
     */
    private String planEmp;

    /**
     * 实际参会人员
     */
    private String actualPerson;

    /**
     * 新增参会人员
     */
    private String newPerson;

    /**
     * 未参会人员
     */
    private String nonePerson;
    /**
     * 参会人
     */
    private String person;

    /**
     * 参会人数
     */
    private Double personNum;

    /**
     * 自我批评的内容
     */
    private String selfCritic;


    /**
     * 他人给予的建议
     */
    private String advice;

    /**
     * 建议人
     */
    private String advicer;

    /**
     * 自我总结
     */
    private String selfsummery;

    /**
     * 会议记录人
     */
    private String recorder;

    /**
     * 会议主持人
     */
    private String meetHost;

    /**
     * 会议组织人
     */
    private String meetOrganor;

    /**
     * 处理实际时间转成string型
     */
    private String dealTime;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMeetNumber() {
        return meetNumber;
    }

    public void setMeetNumber(String meetNumber) {
        this.meetNumber = meetNumber;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getMeetForm() {
        return meetForm;
    }

    public void setMeetForm(String meetForm) {
        this.meetForm = meetForm;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPlanEmp() {
        return planEmp;
    }

    public void setPlanEmp(String planEmp) {
        this.planEmp = planEmp;
    }

    public String getActualPerson() {
        return actualPerson;
    }

    public void setActualPerson(String actualPerson) {
        this.actualPerson = actualPerson;
    }

    public String getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(String newPerson) {
        this.newPerson = newPerson;
    }

    public String getNonePerson() {
        return nonePerson;
    }

    public void setNonePerson(String nonePerson) {
        this.nonePerson = nonePerson;
    }

    public Double getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Double personNum) {
        this.personNum = personNum;
    }

    public String getSelfCritic() {
        return selfCritic;
    }

    public void setSelfCritic(String selfCritic) {
        this.selfCritic = selfCritic;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getAdvicer() {
        return advicer;
    }

    public void setAdvicer(String advicer) {
        this.advicer = advicer;
    }

    public String getSelfsummery() {
        return selfsummery;
    }

    public void setSelfsummery(String selfsummery) {
        this.selfsummery = selfsummery;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getMeetOrganor() {
        return meetOrganor;
    }

    public void setMeetOrganor(String meetOrganor) {
        this.meetOrganor = meetOrganor;
    }

    public String getMeetHost() {
        return meetHost;
    }

    public void setMeetHost(String meetHost) {
        this.meetHost = meetHost;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel = titleLevel;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}