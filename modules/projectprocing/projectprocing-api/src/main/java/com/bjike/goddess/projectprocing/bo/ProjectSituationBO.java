package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 项目情况业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:16 ]
 * @Description: [ 项目情况业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectSituationBO extends BaseBO {

    /**
     * 合同外部项目名称
     */
    private String outerName;

    /**
     * 内部项目名称
     */
    private String innerName;

    /**
     * 合同签订情况
     */
    private String signCondition;

    /**
     * 销售合同号
     */
    private String saleNum;

    /**
     * 派工单编号
     */
    private String depatchNum;

    /**
     * 开工日期
     */
    private String startWorkTime;

    /**
     * 计划完工日期
     */
    private String planCompleteTime;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户负责人
     */
    private String customerCharger;

    /**
     * 客户联系电话
     */
    private String customerTel;

    /**
     * 施工单位
     */
    private String constructionUnit;

    /**
     * 工程督导
     */
    private String enginSupervision;

    /**
     * 工程地点
     */
    private String enginPlace;

    /**
     * 项目经理
     */
    private String projectManager;

    /**
     * 内容
     */
    private String contents;

    /**
     * 完工时间
     */
    private String completeTime;

    /**
     * 完工情况
     */
    private String completeCondition;

    /**
     * 完工报告
     */
    private String completeReport;

    /**
     * 合同跟进
     */
    private String contractFollow;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商务合同外部项目名称id
     */
    private String outerNameId;

    /**
     * 市场信息记录内部项目名称id
     */
    private String innerNameId;

    /**
     * 商务项目合同基本信息对应销售合同号id
     */
    private String saleNumId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getOuterName() {
        return outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName = outerName;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getSignCondition() {
        return signCondition;
    }

    public void setSignCondition(String signCondition) {
        this.signCondition = signCondition;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getDepatchNum() {
        return depatchNum;
    }

    public void setDepatchNum(String depatchNum) {
        this.depatchNum = depatchNum;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getPlanCompleteTime() {
        return planCompleteTime;
    }

    public void setPlanCompleteTime(String planCompleteTime) {
        this.planCompleteTime = planCompleteTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCharger() {
        return customerCharger;
    }

    public void setCustomerCharger(String customerCharger) {
        this.customerCharger = customerCharger;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getEnginSupervision() {
        return enginSupervision;
    }

    public void setEnginSupervision(String enginSupervision) {
        this.enginSupervision = enginSupervision;
    }

    public String getEnginPlace() {
        return enginPlace;
    }

    public void setEnginPlace(String enginPlace) {
        this.enginPlace = enginPlace;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getCompleteCondition() {
        return completeCondition;
    }

    public void setCompleteCondition(String completeCondition) {
        this.completeCondition = completeCondition;
    }

    public String getCompleteReport() {
        return completeReport;
    }

    public void setCompleteReport(String completeReport) {
        this.completeReport = completeReport;
    }

    public String getContractFollow() {
        return contractFollow;
    }

    public void setContractFollow(String contractFollow) {
        this.contractFollow = contractFollow;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOuterNameId() {
        return outerNameId;
    }

    public void setOuterNameId(String outerNameId) {
        this.outerNameId = outerNameId;
    }

    public String getInnerNameId() {
        return innerNameId;
    }

    public void setInnerNameId(String innerNameId) {
        this.innerNameId = innerNameId;
    }

    public String getSaleNumId() {
        return saleNumId;
    }

    public void setSaleNumId(String saleNumId) {
        this.saleNumId = saleNumId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}