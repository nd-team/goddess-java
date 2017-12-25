package com.bjike.goddess.datastore.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 数据存储文件规范业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:12 ]
 * @Description: [ 数据存储文件规范业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FileSpecificationBO extends BaseBO {

    /**
     * 属性简称
     */
    private String attributeAbbreviation;

    /**
     * 类别简称
     */
    private String categoryAbbreviation;

    /**
     * 发布时间
     */
    private String releaseTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 所属项目组
     */
    private String belongProjectGroup;

    /**
     * 内容所属小模块
     */
    private String smallModuleContent;

    /**
     * 内容概述
     */
    private String contentSummary;

    /**
     * 产品名概述
     */
    private String productNameSummary;

    /**
     * 文件名概述
     */
    private String fileNameSummary;

    /**
     * 版本数字
     */
    private String versionNum;

    /**
     * 序号
     */
    private String no;

    /**
     * 备注
     */
    private String remark;


    public String getAttributeAbbreviation() {
        return attributeAbbreviation;
    }

    public void setAttributeAbbreviation(String attributeAbbreviation) {
        this.attributeAbbreviation = attributeAbbreviation;
    }

    public String getCategoryAbbreviation() {
        return categoryAbbreviation;
    }

    public void setCategoryAbbreviation(String categoryAbbreviation) {
        this.categoryAbbreviation = categoryAbbreviation;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getBelongProjectGroup() {
        return belongProjectGroup;
    }

    public void setBelongProjectGroup(String belongProjectGroup) {
        this.belongProjectGroup = belongProjectGroup;
    }

    public String getSmallModuleContent() {
        return smallModuleContent;
    }

    public void setSmallModuleContent(String smallModuleContent) {
        this.smallModuleContent = smallModuleContent;
    }

    public String getContentSummary() {
        return contentSummary;
    }

    public void setContentSummary(String contentSummary) {
        this.contentSummary = contentSummary;
    }

    public String getProductNameSummary() {
        return productNameSummary;
    }

    public void setProductNameSummary(String productNameSummary) {
        this.productNameSummary = productNameSummary;
    }

    public String getFileNameSummary() {
        return fileNameSummary;
    }

    public void setFileNameSummary(String fileNameSummary) {
        this.fileNameSummary = fileNameSummary;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}