package com.bjike.goddess.managementpromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.managementpromotion.enums.ManagerOpinion;

/**
 * 管理等级晋升申请业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 02:04 ]
 * @Description: [ 管理等级晋升申请业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PromotionApplyBO extends BaseBO {

    /**
     * 体系
     */
    private String system;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 岗位
     */
    private String job;

    /**
     * 姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String employeeId;

    /**
     * 分类
     */
    private String classification;

    /**
     * 管理方向
     */
    private String direction;

    /**
     * 技能等级
     */
    private String skillLevel;

    /**
     * 级别
     */
    private String grade;

    /**
     * 申请晋升时间
     */
    private String applyDate;

    /**
     * 在我司工龄(月)
     */
    private Double workAge;

    /**
     * 转正时间
     */
    private String positiveDate;

    /**
     * 当前管理等级
     */
    private String currentLevel;

    /**
     * 在管理岗位时间(月)
     */
    private Double inManageTime;

    /**
     * 已晋升次数
     */
    private Integer promotionNum;

    /**
     * 距上次晋升时长
     */
    private Double lastPromotionTime;

    /**
     * 本次晋升等级
     */
    private String promotionLevel;

    /**
     * 是否符合晋升条件
     */
    private Boolean isConform;

    /**
     * 晋升标准达标数
     */
    private Integer promotionCriteria;

    /**
     * 本次晋升等级获得时间
     */
    private String promotionTakeTime;

    /**
     * 项目经理审核意见
     */
    private String projectManagerOpinion;

    /**
     * 综合资源部规划模块审核意见
     */
    private String resourceDepartmentOpinion;

    /**
     * 运营商务部预算模块审核意见
     */
    private String commerceDepartmentOpinion;

    /**
     * 模块负责人审核意见
     */
    private String modulerOpinion;

    /**
     * 总经办审核意见
     */
    private ManagerOpinion managerOpinion;

    /**
     * 排名
     */
    private Integer rank;

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Double getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Double workAge) {
        this.workAge = workAge;
    }

    public String getPositiveDate() {
        return positiveDate;
    }

    public void setPositiveDate(String positiveDate) {
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

    public String getPromotionTakeTime() {
        return promotionTakeTime;
    }

    public void setPromotionTakeTime(String promotionTakeTime) {
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