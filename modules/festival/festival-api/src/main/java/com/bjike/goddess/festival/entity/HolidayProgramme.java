package com.bjike.goddess.festival.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 法定节假日放假方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:03 ]
 * @Description: [ 法定节假日放假方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "festival_holidayprogramme")
public class HolidayProgramme extends BaseEntity {

    /**
     * 节假日名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '节假日名称'")
    private String name;

    /**
     * 放假时间安排
     */
    @Column(name = "offTime",  columnDefinition = "VARCHAR(255)   COMMENT '放假时间安排'")
    private String offTime;

    /**
     * 是否有加班需求(是/否)
     */
    @Column(name = "overTimeCondition",  columnDefinition = "VARCHAR(255)   COMMENT '是否有加班需求(是/否)'")
    private String overTimeCondition;

    /**
     * 节假日工作安排
     */
    @Column(name = "workArrange",  columnDefinition = "VARCHAR(255)   COMMENT '节假日工作安排'")
    private String workArrange;

    /**
     * 各地区紧急联系人
     */
    @Column(name = "areaRelation",  columnDefinition = "VARCHAR(255)   COMMENT '各地区紧急联系人'")
    private String areaRelation;

    /**
     * 节假日礼品福利
     */
    @Column(name = "festivalWelfare",  columnDefinition = "VARCHAR(255)   COMMENT '节假日礼品福利'")
    private String festivalWelfare;

    /**
     * 是否有活动需求
     */
    @Column(name = "activeQuried", columnDefinition = "VARCHAR(255)   COMMENT '是否有活动需求'")
    private String activeQuried;

    /**
     * 活动安排详情
     */
    @Column(name = "activeDetail",  columnDefinition = "VARCHAR(255)   COMMENT '活动安排详情'")
    private String activeDetail;

    /**
     * 注意事项
     */
    @Column(name = "matterThings",  columnDefinition = "VARCHAR(255)   COMMENT '注意事项'")
    private String matterThings;

    /**
     * 福利模块审批意见
     */
    @Column(name = "welfareAudit",  columnDefinition = "VARCHAR(255)   COMMENT '福利模块审批意见'")
    private String welfareAudit;

    /**
     * 运营商务部审批意见
     */
    @Column(name = "operateAudit",  columnDefinition = "VARCHAR(255)   COMMENT '运营商务部审批意见'")
    private String operateAudit;

    /**
     * 是否通过
     */
    @Column(name = "passCondition",  columnDefinition = "VARCHAR(255)   COMMENT '是否通过'")
    private String passCondition;

    /**
     * 总经办意见
     */
    @Column(name = "managerAdivce",  columnDefinition = "VARCHAR(255)   COMMENT '总经办意见'")
    private String managerAdivce;

    /**
     * 文件编号
     */
    @Column(name = "fileNum",  columnDefinition = "VARCHAR(255)   COMMENT '文件编号'")
    private String fileNum;

    /**
     * 存储位置
     */
    @Column(name = "storagePosition",  columnDefinition = "VARCHAR(255)   COMMENT '存储位置'")
    private String storagePosition;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public String getOverTimeCondition() {
        return overTimeCondition;
    }

    public void setOverTimeCondition(String overTimeCondition) {
        this.overTimeCondition = overTimeCondition;
    }

    public String getWorkArrange() {
        return workArrange;
    }

    public void setWorkArrange(String workArrange) {
        this.workArrange = workArrange;
    }

    public String getAreaRelation() {
        return areaRelation;
    }

    public void setAreaRelation(String areaRelation) {
        this.areaRelation = areaRelation;
    }

    public String getFestivalWelfare() {
        return festivalWelfare;
    }

    public void setFestivalWelfare(String festivalWelfare) {
        this.festivalWelfare = festivalWelfare;
    }

    public String getActiveQuried() {
        return activeQuried;
    }

    public void setActiveQuried(String activeQuried) {
        this.activeQuried = activeQuried;
    }

    public String getActiveDetail() {
        return activeDetail;
    }

    public void setActiveDetail(String activeDetail) {
        this.activeDetail = activeDetail;
    }

    public String getMatterThings() {
        return matterThings;
    }

    public void setMatterThings(String matterThings) {
        this.matterThings = matterThings;
    }

    public String getWelfareAudit() {
        return welfareAudit;
    }

    public void setWelfareAudit(String welfareAudit) {
        this.welfareAudit = welfareAudit;
    }

    public String getOperateAudit() {
        return operateAudit;
    }

    public void setOperateAudit(String operateAudit) {
        this.operateAudit = operateAudit;
    }

    public String getPassCondition() {
        return passCondition;
    }

    public void setPassCondition(String passCondition) {
        this.passCondition = passCondition;
    }

    public String getManagerAdivce() {
        return managerAdivce;
    }

    public void setManagerAdivce(String managerAdivce) {
        this.managerAdivce = managerAdivce;
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    public String getStoragePosition() {
        return storagePosition;
    }

    public void setStoragePosition(String storagePosition) {
        this.storagePosition = storagePosition;
    }




}