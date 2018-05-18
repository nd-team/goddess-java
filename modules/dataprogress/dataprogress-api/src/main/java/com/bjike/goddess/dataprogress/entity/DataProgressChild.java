package com.bjike.goddess.dataprogress.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 资料收集进度管理子表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:04 ]
 * @Description: [ 资料收集进度管理子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "dataprogress_dataprogresschild")
public class DataProgressChild extends BaseEntity {

    /**
     * 年份
     */
    @Column(name = "year", columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "month", columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    /**
     * 周期
     */
    @Column(name = "cycle", columnDefinition = "VARCHAR(255)   COMMENT '周期'")
    private String cycle;

    /**
     * 计划人工(小时)
     */
    @Column(name = "planPeople", columnDefinition = "INT(11)   COMMENT '计划人工(小时)'")
    private Integer planPeople;

    /**
     * 大科目
     */
    @Column(name = "bigSubject", columnDefinition = "VARCHAR(255)   COMMENT '大科目'")
    private String bigSubject;

    /**
     * 小科目
     */
    @Column(name = "smallSubject", columnDefinition = "VARCHAR(255)   COMMENT '小科目'")
    private String smallSubject;

    /**
     * 课件考核课时
     */
    @Column(name = "coursewareAppraisalClass", columnDefinition = "VARCHAR(255)   COMMENT '课件考核课时'")
    private String coursewareAppraisalClass;

    /**
     * 是否已收集
     */
    @Column(name = "is_collection", columnDefinition = "TINYINT(1)  COMMENT '是否已收集'")
    private Boolean collection;

    /**
     * 收集人员
     */
    @Column(name = "collectionPeople", columnDefinition = "VARCHAR(255)   COMMENT '收集人员'")
    private String collectionPeople;

    /**
     * 跟进人员
     */
    @Column(name = "followPeople", columnDefinition = "VARCHAR(255)   COMMENT '跟进人员'")
    private String followPeople;

    /**
     * 实际收集日期
     */
    @Column(name = "actualCollectionTime", columnDefinition = "DATE   COMMENT '实际收集日期'")
    private LocalDate actualCollectionTime;

    /**
     * 实际人工(小时)
     */
    @Column(name = "actualPeople", columnDefinition = "INT(11)   COMMENT '实际人工(小时)'")
    private Integer actualPeople;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;
    /**
     * 资料收集进度主表
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "dataProgressMain_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '资料收集进度主表'")
    private DataProgressMain dataProgressMain;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public LocalDate getActualCollectionTime() {
        return actualCollectionTime;
    }

    public void setActualCollectionTime(LocalDate actualCollectionTime) {
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

    public DataProgressMain getDataProgressMain() {
        return dataProgressMain;
    }

    public void setDataProgressMain(DataProgressMain dataProgressMain) {
        this.dataProgressMain = dataProgressMain;
    }
}