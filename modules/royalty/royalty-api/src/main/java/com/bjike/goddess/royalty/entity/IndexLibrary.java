package com.bjike.goddess.royalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 指标库
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 指标库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "royalty_indexlibrary")
public class IndexLibrary extends BaseEntity {

    /**
     * 指标编号
     */
    @Column(name = "indexNum", nullable = false,unique = true,columnDefinition = "VARCHAR(255)   COMMENT '指标编号'")
    private String indexNum;

    /**
     * 指标维度
     */
    @Column(name = "indexDimension",  columnDefinition = "VARCHAR(255)   COMMENT '指标维度'")
    private String indexDimension;

    /**
     * 指标名称
     */
    @Column(name = "indexName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '指标名称'")
    private String indexName;

    /**
     * 指标描述
     */
    @Column(name = "indexDescription",  columnDefinition = "VARCHAR(255)   COMMENT '指标描述'")
    private String indexDescription;

    /**
     * 意义
     */
    @Column(name = "sense",  columnDefinition = "VARCHAR(255)   COMMENT '意义'")
    private String sense;

    /**
     * 考核部门
     */
    @Column(name = "inspectionDepartment",  columnDefinition = "VARCHAR(255)   COMMENT '考核部门'")
    private String inspectionDepartment;

    /**
     * 适用岗位
     */
    @Column(name = "forPost", columnDefinition = "VARCHAR(255)   COMMENT '适用岗位'")
    private String forPost;

    /**
     * 数据来源
     */
    @Column(name = "dataSource",  columnDefinition = "VARCHAR(255)   COMMENT '数据来源'")
    private String dataSource;
    /**
     * 是否被选用
     */
    @Column(name = "is_choose",  columnDefinition = "TINYINT(1)   COMMENT '是否被选用'")
    private Boolean choose;
    /**
     * 对赌承诺-确认目标值
     */
    @Column(name = "confirmTargetValue", columnDefinition = "VARCHAR(255)   COMMENT '对赌承诺-确认目标值'")
    private String confirmTargetValue;
    /**
     * 达标状态
     */
    @Column(name = "standard",  columnDefinition = "VARCHAR(255)   COMMENT '达标状态'")
    private String standard;


    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexDimension() {
        return indexDimension;
    }

    public void setIndexDimension(String indexDimension) {
        this.indexDimension = indexDimension;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexDescription() {
        return indexDescription;
    }

    public void setIndexDescription(String indexDescription) {
        this.indexDescription = indexDescription;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public String getInspectionDepartment() {
        return inspectionDepartment;
    }

    public void setInspectionDepartment(String inspectionDepartment) {
        this.inspectionDepartment = inspectionDepartment;
    }

    public String getForPost() {
        return forPost;
    }

    public void setForPost(String forPost) {
        this.forPost = forPost;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean getChoose() {
        return choose;
    }

    public void setChoose(Boolean choose) {
        this.choose = choose;
    }

    public String getConfirmTargetValue() {
        return confirmTargetValue;
    }

    public void setConfirmTargetValue(String confirmTargetValue) {
        this.confirmTargetValue = confirmTargetValue;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}