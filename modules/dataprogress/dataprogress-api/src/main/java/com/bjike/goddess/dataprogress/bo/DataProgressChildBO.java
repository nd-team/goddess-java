package com.bjike.goddess.dataprogress.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 资料收集进度管理子表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:04 ]
 * @Description: [ 资料收集进度管理子表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DataProgressChildBO extends BaseBO {

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer name;

    /**
     * 周期
     */
    private String cycle;

    /**
     * 计划人工(小时)
     */
    private Integer planPeople;

    /**
     * 大科目
     */
    private String bigSubject;

    /**
     * 小科目
     */
    private String smallSubject;

    /**
     * 课件考核课时
     */
    private String coursewareAppraisalClass;

    /**
     * 是否已收集
     */
    private Boolean collection;

    /**
     * 收集人员
     */
    private String collectionPeople;

    /**
     * 跟进人员
     */
    private String followPeople;

    /**
     * 实际收集日期
     */
    private String actualCollectionTime;

    /**
     * 实际人工(小时)
     */
    private Integer actualPeople;

    /**
     * 备注
     */
    private String remark;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Integer getPlanPeople() {
        return planPeople;
    }

    public void setPlanPeople(Integer planPeople) {
        this.planPeople = planPeople;
    }

    public String getBigSubject() {
        return bigSubject;
    }

    public void setBigSubject(String bigSubject) {
        this.bigSubject = bigSubject;
    }

    public String getSmallSubject() {
        return smallSubject;
    }

    public void setSmallSubject(String smallSubject) {
        this.smallSubject = smallSubject;
    }

    public String getCoursewareAppraisalClass() {
        return coursewareAppraisalClass;
    }

    public void setCoursewareAppraisalClass(String coursewareAppraisalClass) {
        this.coursewareAppraisalClass = coursewareAppraisalClass;
    }

    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }

    public String getCollectionPeople() {
        return collectionPeople;
    }

    public void setCollectionPeople(String collectionPeople) {
        this.collectionPeople = collectionPeople;
    }

    public String getFollowPeople() {
        return followPeople;
    }

    public void setFollowPeople(String followPeople) {
        this.followPeople = followPeople;
    }

    public String getActualCollectionTime() {
        return actualCollectionTime;
    }

    public void setActualCollectionTime(String actualCollectionTime) {
        this.actualCollectionTime = actualCollectionTime;
    }

    public Integer getActualPeople() {
        return actualPeople;
    }

    public void setActualPeople(Integer actualPeople) {
        this.actualPeople = actualPeople;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}