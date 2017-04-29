package com.bjike.goddess.datastore.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 数据存储编号规范
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:35 ]
 * @Description: [ 数据存储编号规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "datastore_numspecification")
public class NumSpecification extends BaseEntity {

    /**
     * 地区英文代号
     */
    @Column(name = "areaEnglishCode", columnDefinition = "VARCHAR(255)   COMMENT '地区英文代号'")
    private String areaEnglishCode;

    /**
     * 项目组英文代号
     */
    @Column(name = "projectTeamEnglishCode", columnDefinition = "VARCHAR(255)   COMMENT '项目组英文代号'")
    private String projectTeamEnglishCode;

    /**
     * 大模块英文字符
     */
    @Column(name = "largeModuleEnglishCharacter", columnDefinition = "VARCHAR(255)   COMMENT '大模块英文字符'")
    private String largeModuleEnglishCharacter;

    /**
     * 小模块英文字符
     */
    @Column(name = "smallModuleEnglishCharacter", columnDefinition = "VARCHAR(255)   COMMENT '小模块英文字符'")
    private String smallModuleEnglishCharacter;

    /**
     * 小项目数字字段
     */
    @Column(name = "smallProjectNumField", columnDefinition = "VARCHAR(255)   COMMENT '小项目数字字段'")
    private String smallProjectNumField;

    /**
     * 属性数字字段
     */
    @Column(name = "attributeNumField", columnDefinition = "VARCHAR(255)   COMMENT '属性数字字段'")
    private String attributeNumField;

    /**
     * 类型英文代号
     */
    @Column(name = "typeEnglishCode", columnDefinition = "VARCHAR(255)   COMMENT '类型英文代号'")
    private String typeEnglishCode;

    /**
     * 目录数字字段
     */
    @Column(name = "directoryNumField", columnDefinition = "VARCHAR(255)   COMMENT '目录数字字段'")
    private String directoryNumField;

    /**
     * 事件内容
     */
    @Column(name = "eventContent", columnDefinition = "VARCHAR(255)   COMMENT '事件内容'")
    private String eventContent;

    /**
     * 物品内容
     */
    @Column(name = "articleContent", columnDefinition = "VARCHAR(255)   COMMENT '物品内容'")
    private String articleContent;

    /**
     * 文献主题
     */
    @Column(name = "theme", columnDefinition = "VARCHAR(255)   COMMENT '文献主题'")
    private String theme;

    /**
     * 内容概况数字字段
     */
    @Column(name = "contentSummaryNumField", columnDefinition = "VARCHAR(255)   COMMENT '内容概况数字字段'")
    private String contentSummaryNumField;

    /**
     * 序号
     */
    @Column(name = "no", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '序号'")
    private String no;

    /**
     * 地区数字字段
     */
    @Column(name = "areaNumField", columnDefinition = "VARCHAR(255)   COMMENT '地区数字字段'")
    private String areaNumField;

    /**
     * 部门数字字段
     */
    @Column(name = "departmentNumField", columnDefinition = "VARCHAR(255)   COMMENT '部门数字字段'")
    private String departmentNumField;

    /**
     * 员工编号
     */
    @Column(name = "employeeNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String employeeNum;

    /**
     * 岗位数字字段
     */
    @Column(name = "postNumField", columnDefinition = "VARCHAR(255)   COMMENT '岗位数字字段'")
    private String postNumField;

    /**
     * 时间年月日字段
     */
    @Column(name = "timeField", columnDefinition = "VARCHAR(255)   COMMENT '时间年月日字段'")
    private String timeField;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getAreaEnglishCode() {
        return areaEnglishCode;
    }

    public void setAreaEnglishCode(String areaEnglishCode) {
        this.areaEnglishCode = areaEnglishCode;
    }

    public String getProjectTeamEnglishCode() {
        return projectTeamEnglishCode;
    }

    public void setProjectTeamEnglishCode(String projectTeamEnglishCode) {
        this.projectTeamEnglishCode = projectTeamEnglishCode;
    }

    public String getLargeModuleEnglishCharacter() {
        return largeModuleEnglishCharacter;
    }

    public void setLargeModuleEnglishCharacter(String largeModuleEnglishCharacter) {
        this.largeModuleEnglishCharacter = largeModuleEnglishCharacter;
    }

    public String getSmallModuleEnglishCharacter() {
        return smallModuleEnglishCharacter;
    }

    public void setSmallModuleEnglishCharacter(String smallModuleEnglishCharacter) {
        this.smallModuleEnglishCharacter = smallModuleEnglishCharacter;
    }

    public String getSmallProjectNumField() {
        return smallProjectNumField;
    }

    public void setSmallProjectNumField(String smallProjectNumField) {
        this.smallProjectNumField = smallProjectNumField;
    }

    public String getAttributeNumField() {
        return attributeNumField;
    }

    public void setAttributeNumField(String attributeNumField) {
        this.attributeNumField = attributeNumField;
    }

    public String getTypeEnglishCode() {
        return typeEnglishCode;
    }

    public void setTypeEnglishCode(String typeEnglishCode) {
        this.typeEnglishCode = typeEnglishCode;
    }

    public String getDirectoryNumField() {
        return directoryNumField;
    }

    public void setDirectoryNumField(String directoryNumField) {
        this.directoryNumField = directoryNumField;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContentSummaryNumField() {
        return contentSummaryNumField;
    }

    public void setContentSummaryNumField(String contentSummaryNumField) {
        this.contentSummaryNumField = contentSummaryNumField;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getAreaNumField() {
        return areaNumField;
    }

    public void setAreaNumField(String areaNumField) {
        this.areaNumField = areaNumField;
    }

    public String getDepartmentNumField() {
        return departmentNumField;
    }

    public void setDepartmentNumField(String departmentNumField) {
        this.departmentNumField = departmentNumField;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getPostNumField() {
        return postNumField;
    }

    public void setPostNumField(String postNumField) {
        this.postNumField = postNumField;
    }

    public String getTimeField() {
        return timeField;
    }

    public void setTimeField(String timeField) {
        this.timeField = timeField;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}