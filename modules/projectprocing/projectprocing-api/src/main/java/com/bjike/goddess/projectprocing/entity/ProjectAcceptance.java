package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 项目验收情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:54 ]
 * @Description: [ 项目验收情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_projectacceptance")
public class ProjectAcceptance extends BaseEntity {

    /**
     * 合同外部项目名称
     */
    @Column(name = "outerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合同外部项目名称'")
    private String outerName;

    /**
     * 内部项目名称
     */
    @Column(name = "innerName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerName;

    /**
     * 销售合同号
     */
    @Column(name = "saleNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '销售合同号'")
    private String saleNum;

    /**
     * 派工单编号
     */
    @Column(name = "dispatchNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '派工单编号'")
    private String dispatchNum;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 初验申请报告
     */
    @Column(name = "checkApplyReport",  columnDefinition = "VARCHAR(255)   COMMENT '初验申请报告'")
    private String checkApplyReport;

    /**
     * 初验时间
     */
    @Column(name = "startCheckTime",  columnDefinition = "DATE   COMMENT '初验时间'")
    private LocalDate startCheckTime;

    /**
     * 初验内容
     */
    @Column(name = "startCheckContent",  columnDefinition = "VARCHAR(255)   COMMENT '初验内容'")
    private String startCheckContent;

    /**
     * 初验方案
     */
    @Column(name = "startCheckDecision",  columnDefinition = "VARCHAR(255)   COMMENT '初验方案'")
    private String startCheckDecision;

    /**
     * 现场工作联络单
     */
    @Column(name = "workOrder",  columnDefinition = "VARCHAR(255)   COMMENT '现场工作联络单'")
    private String workOrder;

    /**
     * 客户资料移交清单
     */
    @Column(name = "customerTransferList",  columnDefinition = "VARCHAR(255)   COMMENT '客户资料移交清单'")
    private String customerTransferList;

    /**
     * 工程备忘录
     */
    @Column(name = "enginMemorandum",  columnDefinition = "VARCHAR(255)   COMMENT '工程备忘录'")
    private String enginMemorandum;

    /**
     * 设备安装报告
     */
    @Column(name = "deviceInstallReport",  columnDefinition = "VARCHAR(255)   COMMENT '设备安装报告'")
    private String deviceInstallReport;

    /**
     * 系统初验证书
     */
    @Column(name = "systemCheckBook",  columnDefinition = "VARCHAR(255)   COMMENT '系统初验证书'")
    private String systemCheckBook;

    /**
     * 工程遗留问题清单
     */
    @Column(name = "enginLeaveList",  columnDefinition = "VARCHAR(255)   COMMENT '工程遗留问题清单'")
    private String enginLeaveList;

    /**
     * 技术遗留问题清单
     */
    @Column(name = "technicalLeaveList",  columnDefinition = "VARCHAR(255)   COMMENT '技术遗留问题清单'")
    private String technicalLeaveList;

    /**
     * 工程竣工验收报告
     */
    @Column(name = "projectCompleteReport",  columnDefinition = "VARCHAR(255)   COMMENT '工程竣工验收报告'")
    private String projectCompleteReport;

    /**
     * 商务合同外部项目名称id
     */
    @Column(name = "outerNameId",  columnDefinition = "VARCHAR(255)   COMMENT '商务合同外部项目名称id'")
    private String outerNameId;

    /**
     * 市场信息记录内部项目名称id
     */
    @Column(name = "innerNameId",  columnDefinition = "VARCHAR(255)   COMMENT '市场信息记录内部项目名称id'")
    private String innerNameId;

    /**
     * 商务项目合同基本信息对应销售合同号id
     */
    @Column(name = "saleNumId",  columnDefinition = "VARCHAR(255)   COMMENT '商务项目合同基本信息对应销售合同号id'")
    private String saleNumId;



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

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(String dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCheckApplyReport() {
        return checkApplyReport;
    }

    public void setCheckApplyReport(String checkApplyReport) {
        this.checkApplyReport = checkApplyReport;
    }

    public LocalDate getStartCheckTime() {
        return startCheckTime;
    }

    public void setStartCheckTime(LocalDate startCheckTime) {
        this.startCheckTime = startCheckTime;
    }

    public String getStartCheckContent() {
        return startCheckContent;
    }

    public void setStartCheckContent(String startCheckContent) {
        this.startCheckContent = startCheckContent;
    }

    public String getStartCheckDecision() {
        return startCheckDecision;
    }

    public void setStartCheckDecision(String startCheckDecision) {
        this.startCheckDecision = startCheckDecision;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public String getCustomerTransferList() {
        return customerTransferList;
    }

    public void setCustomerTransferList(String customerTransferList) {
        this.customerTransferList = customerTransferList;
    }

    public String getEnginMemorandum() {
        return enginMemorandum;
    }

    public void setEnginMemorandum(String enginMemorandum) {
        this.enginMemorandum = enginMemorandum;
    }

    public String getDeviceInstallReport() {
        return deviceInstallReport;
    }

    public void setDeviceInstallReport(String deviceInstallReport) {
        this.deviceInstallReport = deviceInstallReport;
    }

    public String getSystemCheckBook() {
        return systemCheckBook;
    }

    public void setSystemCheckBook(String systemCheckBook) {
        this.systemCheckBook = systemCheckBook;
    }

    public String getEnginLeaveList() {
        return enginLeaveList;
    }

    public void setEnginLeaveList(String enginLeaveList) {
        this.enginLeaveList = enginLeaveList;
    }

    public String getTechnicalLeaveList() {
        return technicalLeaveList;
    }

    public void setTechnicalLeaveList(String technicalLeaveList) {
        this.technicalLeaveList = technicalLeaveList;
    }

    public String getProjectCompleteReport() {
        return projectCompleteReport;
    }

    public void setProjectCompleteReport(String projectCompleteReport) {
        this.projectCompleteReport = projectCompleteReport;
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

}