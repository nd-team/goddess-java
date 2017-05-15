package com.bjike.goddess.rotation.vo;

import com.bjike.goddess.rotation.enums.AuditType;

/**
 * 岗位轮换推荐表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:28 ]
 * @Description: [ 岗位轮换推荐表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendRotationVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String username;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 目前岗位层级
     */
    private String arrangement;

    /**
     * 举荐轮换等级数据id
     */
    private String applyLevelId;

    /**
     * 举荐轮换等级
     */
    private String applyLevelArrangement;

    /**
     * 举荐原因
     */
    private String reason;

    /**
     * 举荐人
     */
    private String recommend;

    /**
     * 举荐时间
     */
    private String recommendTime;

    /**
     * 轮换后岗位等级数据id
     */
    private String rotationLevelId;

    /**
     * 轮换后岗位等级
     */
    private String rotationLevelArrangement;

    /**
     * 总经办
     */
    private String general;

    /**
     * 总经办意见
     */
    private String opinion;

    /**
     * 是否通过
     */
    private AuditType audit;

    /**
     * 轮换时间
     */
    private String rotationDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getApplyLevelId() {
        return applyLevelId;
    }

    public void setApplyLevelId(String applyLevelId) {
        this.applyLevelId = applyLevelId;
    }

    public String getApplyLevelArrangement() {
        return applyLevelArrangement;
    }

    public void setApplyLevelArrangement(String applyLevelArrangement) {
        this.applyLevelArrangement = applyLevelArrangement;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(String recommendTime) {
        this.recommendTime = recommendTime;
    }

    public String getRotationLevelId() {
        return rotationLevelId;
    }

    public void setRotationLevelId(String rotationLevelId) {
        this.rotationLevelId = rotationLevelId;
    }

    public String getRotationLevelArrangement() {
        return rotationLevelArrangement;
    }

    public void setRotationLevelArrangement(String rotationLevelArrangement) {
        this.rotationLevelArrangement = rotationLevelArrangement;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public AuditType getAudit() {
        return audit;
    }

    public void setAudit(AuditType audit) {
        this.audit = audit;
    }

    public String getRotationDate() {
        return rotationDate;
    }

    public void setRotationDate(String rotationDate) {
        this.rotationDate = rotationDate;
    }
}