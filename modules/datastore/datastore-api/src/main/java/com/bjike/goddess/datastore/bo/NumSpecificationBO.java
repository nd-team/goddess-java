package com.bjike.goddess.datastore.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 数据存储编号规范业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:35 ]
 * @Description: [ 数据存储编号规范业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NumSpecificationBO extends BaseBO {

    /**
     * 地区英文代号
     */
    private String areaEnglishCode;

    /**
     * 项目组英文代号
     */
    private String projectTeamEnglishCode;

    /**
     * 大模块英文字符
     */
    private String largeModuleEnglishCharacter;

    /**
     * 小模块英文字符
     */
    private String smallModuleEnglishCharacter;

    /**
     * 小项目数字字段
     */
    private String smallProjectNumField;

    /**
     * 属性数字字段
     */
    private String attributeNumField;

    /**
     * 类型英文代号
     */
    private String typeEnglishCode;

    /**
     * 目录数字字段
     */
    private String directoryNumField;

    /**
     * 事件内容
     */
    private String eventContent;

    /**
     * 物品内容
     */
    private String articleContent;

    /**
     * 文献主题
     */
    private String theme;

    /**
     * 内容概况数字字段
     */
    private String contentSummaryNumField;

    /**
     * 序号
     */
    private String no;

    /**
     * 地区数字字段
     */
    private String areaNumField;

    /**
     * 部门数字字段
     */
    private String departmentNumField;

    /**
     * 员工编号
     */
    private String employeeNum;

    /**
     * 岗位数字字段
     */
    private String postNumField;

    /**
     * 时间年月日字段
     */
    private String timeField;

    /**
     * 备注
     */
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