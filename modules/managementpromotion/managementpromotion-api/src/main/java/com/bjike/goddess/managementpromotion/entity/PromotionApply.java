package com.bjike.goddess.managementpromotion.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.managementpromotion.enums.ManagerOpinion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 管理等级晋升申请
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 02:04 ]
 * @Description: [ 管理等级晋升申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "managementpromotion_promotionapply")
public class PromotionApply extends BaseEntity {

    /**
     * 体系
     */
    @Column(name = "system", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '体系'")
    private String system;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 岗位
     */
    @Column(name = "job", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String job;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "employeeId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '员工编号'")
    private String employeeId;

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
     * 申请晋升时间
     */
    @Column(name = "applyDate", nullable = false, columnDefinition = "DATE   COMMENT '申请晋升时间'")
    private LocalDate applyDate;

    /**
     * 在我司工龄(月)
     */
    @Column(name = "workAge", columnDefinition = "DECIMAL(10,2)   COMMENT '在我司工龄(月)'")
    private Double workAge;

    /**
     * 转正时间
     */
    @Column(name = "positiveDate", columnDefinition = "DATE   COMMENT '转正时间'")
    private LocalDate positiveDate;

    /**
     * 当前管理等级
     */
    @Column(name = "currentLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '当前管理等级'")
    private String currentLevel;

    /**
     * 在管理岗位时间(月)
     */
    @Column(name = "inManageTime", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '在管理岗位时间(月)'")
    private Double inManageTime;

    /**
     * 已晋升次数
     */
    @Column(name = "promotionNum", nullable = false, columnDefinition = "INT(11)   COMMENT '已晋升次数'")
    private Integer promotionNum;

    /**
     * 距上次晋升时长
     */
    @Column(name = "lastPromotionTime",columnDefinition = "DECIMAL(10,2)   COMMENT '距上次晋升时长'")
    private Double lastPromotionTime;

    /**
     * 本次晋升等级
     */
    @Column(name = "promotionLevel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '本次晋升等级'")
    private String promotionLevel;

    /**
     * 是否符合晋升条件
     */
    @Column(name = "is_Conform", columnDefinition = "TINYINT(1) COMMENT '是否符合晋升条件'")
    private Boolean isConform;

    /**
     * 晋升标准达标数
     */
    @Column(name = "promotionCriteria", columnDefinition = "INT(11)   COMMENT '晋升标准达标数'")
    private Integer promotionCriteria;

    /**
     * 本次晋升等级获得时间
     */
    @Column(name = "promotionTakeTime", columnDefinition = "DATE   COMMENT '本次晋升等级获得时间'")
    private LocalDate promotionTakeTime;

    /**
     * 项目经理审核意见
     */
    @Column(name = "projectManagerOpinion", columnDefinition = "VARCHAR(255)   COMMENT '项目经理审核意见'")
    private String projectManagerOpinion;

    /**
     * 综合资源部规划模块审核意见
     */
    @Column(name = "resourceDepartmentOpinion", columnDefinition = "VARCHAR(255)   COMMENT '综合资源部规划模块审核意见'")
    private String resourceDepartmentOpinion;

    /**
     * 运营商务部预算模块审核意见
     */
    @Column(name = "commerceDepartmentOpinion", columnDefinition = "VARCHAR(255)   COMMENT '运营商务部预算模块审核意见'")
    private String commerceDepartmentOpinion;

    /**
     * 模块负责人审核意见
     */
    @Column(name = "modulerOpinion", columnDefinition = "VARCHAR(255)   COMMENT '模块负责人审核意见'")
    private String modulerOpinion;

    /**
     * 总经办审核意见
     */
    @Column(name = "managerOpinion", columnDefinition = "TINYINT(2)   COMMENT '总经办审核意见'", insertable = false)
    private ManagerOpinion managerOpinion;

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public Double getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Double workAge) {
        this.workAge = workAge;
    }

    public LocalDate getPositiveDate() {
        return positiveDate;
    }

    public void setPositiveDate(LocalDate positiveDate) {
        this.positiveDate = positiveDate;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Double getInManageTime() {
        return inManageTime;
    }

    public void setInManageTime(Double inManageTime) {
        this.inManageTime = inManageTime;
    }

    public Integer getPromotionNum() {
        return promotionNum;
    }

    public void setPromotionNum(Integer promotionNum) {
        this.promotionNum = promotionNum;
    }

    public Double getLastPromotionTime() {
        return lastPromotionTime;
    }

    public void setLastPromotionTime(Double lastPromotionTime) {
        this.lastPromotionTime = lastPromotionTime;
    }

    public String getPromotionLevel() {
        return promotionLevel;
    }

    public void setPromotionLevel(String promotionLevel) {
        this.promotionLevel = promotionLevel;
    }

    public Boolean getIsConform() {
        return isConform;
    }

    public void setIsConform(Boolean isConform) {
        this.isConform = isConform;
    }

    public Integer getPromotionCriteria() {
        return promotionCriteria;
    }

    public void setPromotionCriteria(Integer promotionCriteria) {
        this.promotionCriteria = promotionCriteria;
    }

    public LocalDate getPromotionTakeTime() {
        return promotionTakeTime;
    }

    public void setPromotionTakeTime(LocalDate promotionTakeTime) {
        this.promotionTakeTime = promotionTakeTime;
    }

    public String getProjectManagerOpinion() {
        return projectManagerOpinion;
    }

    public void setProjectManagerOpinion(String projectManagerOpinion) {
        this.projectManagerOpinion = projectManagerOpinion;
    }

    public String getResourceDepartmentOpinion() {
        return resourceDepartmentOpinion;
    }

    public void setResourceDepartmentOpinion(String resourceDepartmentOpinion) {
        this.resourceDepartmentOpinion = resourceDepartmentOpinion;
    }

    public String getCommerceDepartmentOpinion() {
        return commerceDepartmentOpinion;
    }

    public void setCommerceDepartmentOpinion(String commerceDepartmentOpinion) {
        this.commerceDepartmentOpinion = commerceDepartmentOpinion;
    }

    public String getModulerOpinion() {
        return modulerOpinion;
    }

    public void setModulerOpinion(String modulerOpinion) {
        this.modulerOpinion = modulerOpinion;
    }

    public ManagerOpinion getManagerOpinion() {
        return managerOpinion;
    }

    public void setManagerOpinion(ManagerOpinion managerOpinion) {
        this.managerOpinion = managerOpinion;
    }
}