package com.bjike.goddess.businessinteraction.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 洽谈详情
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:27 ]
 * @Description: [ 洽谈详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessinteraction_talkdetail")
public class TalkDetail extends BaseEntity {

    /**
     * 需求方公司
     */
    @Column(name = "demandCompany", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '需求方公司'")
    private String demandCompany;

    /**
     * 合作对象公司名称
     */
    @Column(name = "cooperCompany", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合作对象公司名称'")
    private String cooperCompany;

    /**
     * 合作方式(直接合作 ,中介)
     */
    @Column(name = "cooperWay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合作方式(直接合作 ,中介)'")
    private String cooperWay;

    /**
     * 合作时间
     */
    @Column(name = "cooperTime",columnDefinition = "VARCHAR(255)   COMMENT '合作时间'")
    private String cooperTime;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 业务方向
     */
    @Column(name = "businessTarget",  columnDefinition = "VARCHAR(255)   COMMENT '业务方向'")
    private String businessTarget;

    /**
     * 价格
     */
    @Column(name = "price",  columnDefinition = "VARCHAR(255)   COMMENT '价格'")
    private String price;

    /**
     * 工程名称
     */
    @Column(name = "projectName",  columnDefinition = "VARCHAR(255)   COMMENT '工程名称'")
    private String projectName;

    /**
     * 规模
     */
    @Column(name = "size",  columnDefinition = "VARCHAR(255)   COMMENT '规模'")
    private String size;

    /**
     * 人工
     */
    @Column(name = "artificial",  columnDefinition = "VARCHAR(255)   COMMENT '人工'")
    private String artificial;

    /**
     * 服务费用
     */
    @Column(name = "serviceFee",  columnDefinition = "VARCHAR(255)   COMMENT '服务费用'")
    private String serviceFee;

    /**
     * 中介费用
     */
    @Column(name = "middleFee",  columnDefinition = "VARCHAR(255)   COMMENT '中介费用'")
    private String middleFee;

    /**
     * 是否达成合作(已达成/未达成)
     */
    @Column(name = "cooperCondition",  columnDefinition = "VARCHAR(255)   COMMENT '是否达成合作(已达成/未达成)'")
    private String cooperCondition;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getDemandCompany() {
        return demandCompany;
    }

    public void setDemandCompany(String demandCompany) {
        this.demandCompany = demandCompany;
    }

    public String getCooperCompany() {
        return cooperCompany;
    }

    public void setCooperCompany(String cooperCompany) {
        this.cooperCompany = cooperCompany;
    }

    public String getCooperWay() {
        return cooperWay;
    }

    public void setCooperWay(String cooperWay) {
        this.cooperWay = cooperWay;
    }

    public String getCooperTime() {
        return cooperTime;
    }

    public void setCooperTime(String cooperTime) {
        this.cooperTime = cooperTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessTarget() {
        return businessTarget;
    }

    public void setBusinessTarget(String businessTarget) {
        this.businessTarget = businessTarget;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getArtificial() {
        return artificial;
    }

    public void setArtificial(String artificial) {
        this.artificial = artificial;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getMiddleFee() {
        return middleFee;
    }

    public void setMiddleFee(String middleFee) {
        this.middleFee = middleFee;
    }

    public String getCooperCondition() {
        return cooperCondition;
    }

    public void setCooperCondition(String cooperCondition) {
        this.cooperCondition = cooperCondition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}