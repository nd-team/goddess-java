package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 补助方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:27 ]
 * @Description: [ 补助方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_assistanceplan")
public class AssistancePlan extends BaseEntity {

    /**
     * 方案序号
     */
    @Column(name = "planNum", nullable = false, unique = true , columnDefinition = "VARCHAR(255)   COMMENT '方案序号'")
    private String planNum;
    /**
     * 序号
     */
    @Column(name = "seriNum", nullable = false, unique = true , columnDefinition = "INT   COMMENT '序号'")
    private Integer seriNum;

    /**
     * 补助类型名称
     */
    @Column(name = "typeName",  columnDefinition = "VARCHAR(255)   COMMENT '补助类型名称'")
    private String typeName;

    /**
     * 补助目的
     */
    @Column(name = "targetContent",  columnDefinition = "VARCHAR(255)   COMMENT '补助目的'")
    private String targetContent;

    /**
     * 补助对象(全体员工/一线实施项目)
     */
    @Column(name = "helpObject",  columnDefinition = "VARCHAR(255)   COMMENT '补助对象(全体员工/一线实施项目)'")
    private String helpObject;

    /**
     * 补助开始时间
     */
    @Column(name = "helpStartTime",  columnDefinition = "DATE   COMMENT '补助开始时间'")
    private LocalDate helpStartTime;

    /**
     * 补助结束时间
     */
    @Column(name = "helpEndTime",  columnDefinition = "DATE   COMMENT '补助结束时间'")
    private LocalDate helpEndTime;

    /**
     * 补助条件
     */
    @Column(name = "helpCondition",  columnDefinition = "VARCHAR(255)   COMMENT '补助条件'")
    private String helpCondition;

    /**
     * 条件依据明细
     */
    @Column(name = "detail",  columnDefinition = "VARCHAR(255)   COMMENT '条件依据明细'")
    private String detail;

    /**
     * 数据来源
     */
    @Column(name = "dataOragin",  columnDefinition = "VARCHAR(255)   COMMENT '数据来源'")
    private String dataOragin;

    /**
     * 补助内容
     */
    @Column(name = "helpContent",  columnDefinition = "VARCHAR(255)   COMMENT '补助内容'")
    private String helpContent;

    /**
     * 内容明细
     */
    @Column(name = "contentDetail",  columnDefinition = "VARCHAR(255)   COMMENT '内容明细'")
    private String contentDetail;

    /**
     * 补助发放形式
     */
    @Column(name = "helpForm",  columnDefinition = "VARCHAR(255)   COMMENT '补助发放形式'")
    private String helpForm;

    /**
     * 补助发放时间
     */
    @Column(name = "helpGiveTime",  columnDefinition = "DATE   COMMENT '补助发放时间'")
    private LocalDate helpGiveTime;

    /**
     * 综合资源部福利模块意见
     */
    @Column(name = "warefaleAdvice",  columnDefinition = "VARCHAR(255)   COMMENT '综合资源部福利模块意见'")
    private String warefaleAdvice;

    /**
     * 运营财务部意见
     */
    @Column(name = "finiceAdvice",  columnDefinition = "VARCHAR(255)   COMMENT '运营财务部意见'")
    private String finiceAdvice;

    /**
     * 总经办意见
     */
    @Column(name = "manageAdvice",  columnDefinition = "VARCHAR(255)   COMMENT '总经办意见'")
    private String manageAdvice;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public Integer getSeriNum() {
        return seriNum;
    }

    public void setSeriNum(Integer seriNum) {
        this.seriNum = seriNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTargetContent() {
        return targetContent;
    }

    public void setTargetContent(String targetContent) {
        this.targetContent = targetContent;
    }

    public String getHelpObject() {
        return helpObject;
    }

    public void setHelpObject(String helpObject) {
        this.helpObject = helpObject;
    }

    public LocalDate getHelpStartTime() {
        return helpStartTime;
    }

    public void setHelpStartTime(LocalDate helpStartTime) {
        this.helpStartTime = helpStartTime;
    }

    public LocalDate getHelpEndTime() {
        return helpEndTime;
    }

    public void setHelpEndTime(LocalDate helpEndTime) {
        this.helpEndTime = helpEndTime;
    }

    public String getHelpCondition() {
        return helpCondition;
    }

    public void setHelpCondition(String helpCondition) {
        this.helpCondition = helpCondition;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDataOragin() {
        return dataOragin;
    }

    public void setDataOragin(String dataOragin) {
        this.dataOragin = dataOragin;
    }

    public String getHelpContent() {
        return helpContent;
    }

    public void setHelpContent(String helpContent) {
        this.helpContent = helpContent;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getHelpForm() {
        return helpForm;
    }

    public void setHelpForm(String helpForm) {
        this.helpForm = helpForm;
    }

    public LocalDate getHelpGiveTime() {
        return helpGiveTime;
    }

    public void setHelpGiveTime(LocalDate helpGiveTime) {
        this.helpGiveTime = helpGiveTime;
    }

    public String getWarefaleAdvice() {
        return warefaleAdvice;
    }

    public void setWarefaleAdvice(String warefaleAdvice) {
        this.warefaleAdvice = warefaleAdvice;
    }

    public String getFiniceAdvice() {
        return finiceAdvice;
    }

    public void setFiniceAdvice(String finiceAdvice) {
        this.finiceAdvice = finiceAdvice;
    }

    public String getManageAdvice() {
        return manageAdvice;
    }

    public void setManageAdvice(String manageAdvice) {
        this.manageAdvice = manageAdvice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}