package com.bjike.goddess.datastore.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 数据存储文件规范
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:12 ]
 * @Description: [ 数据存储文件规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "datastore_filespecification")
public class FileSpecification extends BaseEntity {

    /**
     * 属性简称
     */
    @Column(name = "attributeAbbreviation",  columnDefinition = "VARCHAR(255)   COMMENT '属性简称'")
    private String attributeAbbreviation;

    /**
     * 类别简称
     */
    @Column(name = "categoryAbbreviation",  columnDefinition = "VARCHAR(255)   COMMENT '类别简称'")
    private String categoryAbbreviation;

    /**
     * 发布时间
     */
    @Column(name = "releaseTime", nullable = false, columnDefinition = "DATETIME   COMMENT '发布时间'")
    private LocalDateTime releaseTime;

    /**
     * 更新时间
     */
    @Column(name = "updateTime", nullable = false, columnDefinition = "DATETIME   COMMENT '更新时间'")
    private LocalDateTime updateTime;

    /**
     * 所属项目组
     */
    @Column(name = "belongProjectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '所属项目组'")
    private String belongProjectGroup;

    /**
     * 内容所属小模块
     */
    @Column(name = "smallModuleContent",  columnDefinition = "VARCHAR(255)   COMMENT '内容所属小模块'")
    private String smallModuleContent;

    /**
     * 内容概述
     */
    @Column(name = "contentSummary",  columnDefinition = "VARCHAR(255)   COMMENT '内容概述'")
    private String contentSummary;

    /**
     * 产品名概述
     */
    @Column(name = "productNameSummary",  columnDefinition = "VARCHAR(255)   COMMENT '产品名概述'")
    private String productNameSummary;

    /**
     * 文件名概述
     */
    @Column(name = "fileNameSummary",  columnDefinition = "VARCHAR(255)   COMMENT '文件名概述'")
    private String fileNameSummary;

    /**
     * 版本数字
     */
    @Column(name = "versionNum",  columnDefinition = "VARCHAR(255)   COMMENT '版本数字'")
    private String versionNum;

    /**
     * 序号
     */
    @Column(name = "no", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '序号'")
    private String no;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
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

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
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