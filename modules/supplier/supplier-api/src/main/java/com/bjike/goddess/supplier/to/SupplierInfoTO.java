package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.supplier.enums.InfoSource;
import com.bjike.goddess.supplier.enums.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 供应商信息管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupplierInfoTO extends BaseTO {

    /**
     * 供应商信息收集时间
     */
    @NotBlank(message = "供应商信息收集时间不能为空", groups = {ADD.class, EDIT.class})
    private String infoCollectDate;

    /**
     * 供应商信息来源
     */
    @NotNull(message = "供应商信息来源不能为空", groups = {ADD.class, EDIT.class})
    private InfoSource infoSource;

    /**
     * 市场编号
     */
    @NotBlank(message = "市场编号不能为空", groups = {ADD.class, EDIT.class})
    private String marketNum;

    /**
     * 业务类型
     */
    @NotBlank(message = "业务类型不能为空", groups = {ADD.class, EDIT.class})
    private String bussType;

    /**
     * 供应商编号
     */
    @NotBlank(message = "供应商编号不能为空", groups = {ADD.class, EDIT.class})
    private String supplierNum;

    /**
     * 供应商地区
     */
    @NotBlank(message = "供应商地区不能为空", groups = {ADD.class, EDIT.class})
    private String supplierArea;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = {ADD.class, EDIT.class})
    private String supplierName;

    /**
     * 供应商类型
     */
    @NotBlank(message = "供应商类型不能为空", groups = {ADD.class, EDIT.class})
    private String supplierType;

    /**
     * 经营范围
     */
    private String bussScope;

    /**
     * 主要产品和服务
     */
    private String majorProducts;

    /**
     * 业务联络人
     */
    @NotBlank(message = "业务联络人不能为空", groups = {ADD.class, EDIT.class})
    private String bussLiaison;

    /**
     * 职务
     */
    private String duty;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = {ADD.class, EDIT.class})
    private String contactNum;

    /**
     * 邮箱
     */
    private String mailBox;

    /**
     * 传真
     */
    private String fax;

    /**
     * 信息完成度
     */
    private String infoCompletion;

    /**
     * 是否上传营业执照附件
     */
    private Boolean uploadBusinLicense;

    /**
     * 是否上传资质附件
     */
    private Boolean uploadQualifition;

    /**
     * 资质等级
     */
    private String levelQualifition;

    /**
     * 是否确定合作
     */
    private Boolean deterCooper;

    /**
     * 最新外包合同签订时间
     */
    private String newSigningDate;

    /**
     * 最新外包合同截止时间
     */
    private String newCutoffDate;

    /**
     * 是否付款完成
     */
    private Boolean payComplete;

    /**
     * 结算完成时间
     */
    private String settlementCompleteDate;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = {ADD.class, EDIT.class})
    private Status status;

    /**
     * 已合作次数
     */
    private Integer cooperationNum;

    /**
     * 供应商信息是否完善
     */
    @NotNull(message = "供应商信息是否完善不能为空", groups = {ADD.class, EDIT.class})
    private Boolean infoPerfecting;

    public String getInfoCollectDate() {
        return infoCollectDate;
    }

    public void setInfoCollectDate(String infoCollectDate) {
        this.infoCollectDate = infoCollectDate;
    }

    public InfoSource getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(InfoSource infoSource) {
        this.infoSource = infoSource;
    }

    public String getMarketNum() {
        return marketNum;
    }

    public void setMarketNum(String marketNum) {
        this.marketNum = marketNum;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public String getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(String supplierNum) {
        this.supplierNum = supplierNum;
    }

    public String getSupplierArea() {
        return supplierArea;
    }

    public void setSupplierArea(String supplierArea) {
        this.supplierArea = supplierArea;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getBussScope() {
        return bussScope;
    }

    public void setBussScope(String bussScope) {
        this.bussScope = bussScope;
    }

    public String getMajorProducts() {
        return majorProducts;
    }

    public void setMajorProducts(String majorProducts) {
        this.majorProducts = majorProducts;
    }

    public String getBussLiaison() {
        return bussLiaison;
    }

    public void setBussLiaison(String bussLiaison) {
        this.bussLiaison = bussLiaison;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getInfoCompletion() {
        return infoCompletion;
    }

    public void setInfoCompletion(String infoCompletion) {
        this.infoCompletion = infoCompletion;
    }

    public Boolean getUploadBusinLicense() {
        return uploadBusinLicense;
    }

    public void setUploadBusinLicense(Boolean uploadBusinLicense) {
        this.uploadBusinLicense = uploadBusinLicense;
    }

    public Boolean getUploadQualifition() {
        return uploadQualifition;
    }

    public void setUploadQualifition(Boolean uploadQualifition) {
        this.uploadQualifition = uploadQualifition;
    }

    public String getLevelQualifition() {
        return levelQualifition;
    }

    public void setLevelQualifition(String levelQualifition) {
        this.levelQualifition = levelQualifition;
    }

    public Boolean getDeterCooper() {
        return deterCooper;
    }

    public void setDeterCooper(Boolean deterCooper) {
        this.deterCooper = deterCooper;
    }

    public String getNewSigningDate() {
        return newSigningDate;
    }

    public void setNewSigningDate(String newSigningDate) {
        this.newSigningDate = newSigningDate;
    }

    public String getNewCutoffDate() {
        return newCutoffDate;
    }

    public void setNewCutoffDate(String newCutoffDate) {
        this.newCutoffDate = newCutoffDate;
    }

    public Boolean getPayComplete() {
        return payComplete;
    }

    public void setPayComplete(Boolean payComplete) {
        this.payComplete = payComplete;
    }

    public String getSettlementCompleteDate() {
        return settlementCompleteDate;
    }

    public void setSettlementCompleteDate(String settlementCompleteDate) {
        this.settlementCompleteDate = settlementCompleteDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCooperationNum() {
        return cooperationNum;
    }

    public void setCooperationNum(Integer cooperationNum) {
        this.cooperationNum = cooperationNum;
    }

    public Boolean getInfoPerfecting() {
        return infoPerfecting;
    }

    public void setInfoPerfecting(Boolean infoPerfecting) {
        this.infoPerfecting = infoPerfecting;
    }
}