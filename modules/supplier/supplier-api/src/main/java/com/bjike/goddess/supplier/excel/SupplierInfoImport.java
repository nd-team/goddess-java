package com.bjike.goddess.supplier.excel;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.supplier.enums.InfoSource;
import com.bjike.goddess.supplier.enums.Status;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 供应商信息管理导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SupplierInfoImport extends BaseTO {

    /**
     * 供应商信息收集时间
     */
    @ExcelHeader(name = "供应商信息收集时间",notNull = true)
    private String infoCollectDate;

    /**
     * 供应商信息来源
     */
    @ExcelHeader(name = "供应商信息来源",notNull = true)
    private InfoSource infoSource;

    /**
     * 市场编号
     */
    @ExcelHeader(name = "市场编号",notNull = true)
    private String marketNum;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型",notNull = true)
    private String bussType;

    /**
     * 供应商编号
     */
    @ExcelHeader(name = "供应商编号",notNull = true)
    private String supplierNum;

    /**
     * 供应商地区
     */
    @ExcelHeader(name = "供应商地区",notNull = true)
    private String supplierArea;

    /**
     * 供应商名称
     */
    @ExcelHeader(name = "供应商名称",notNull = true)
    private String supplierName;

    /**
     * 供应商类型
     */
    @ExcelHeader(name = "供应商类型",notNull = true)
    private String supplierType;

    /**
     * 经营范围
     */
    @ExcelHeader(name = "经营范围")
    private String bussScope;

    /**
     * 主要产品和服务
     */
    @ExcelHeader(name = "主要产品和服务")
    private String majorProducts;

    /**
     * 业务联络人
     */
    @ExcelHeader(name = "业务联络人",notNull = true)
    private String bussLiaison;

    /**
     * 职务
     */
    @ExcelHeader(name = "职务")
    private String duty;

    /**
     * 联系电话
     */
    @ExcelHeader(name = "联系电话",notNull = true)
    private String contactNum;

    /**
     * 邮箱
     */
    @ExcelHeader(name = "邮箱")
    private String mailBox;

    /**
     * 传真
     */
    @ExcelHeader(name = "传真")
    private String fax;

    /**
     * 信息完成度
     */
    @ExcelHeader(name = "信息完成度")
    private String infoCompletion;

    /**
     * 是否上传营业执照附件
     */
    @ExcelHeader(name = "是否上传营业执照附件")
    private String uploadBusinLicense;

    /**
     * 是否上传资质附件
     */
    @ExcelHeader(name = "是否上传资质附件")
    private String uploadQualifition;

    /**
     * 资质等级
     */
    @ExcelHeader(name = "资质等级")
    private String levelQualifition;

    /**
     * 是否确定合作
     */
    @ExcelHeader(name = "是否确定合作")
    private String deterCooper;

    /**
     * 最新外包合同签订时间
     */
    @ExcelHeader(name = "最新外包合同签订时间")
    private String newSigningDate;

    /**
     * 最新外包合同截止时间
     */
    @ExcelHeader(name = "最新外包合同截止时间")
    private String newCutoffDate;

    /**
     * 是否付款完成
     */
    @ExcelHeader(name = "是否付款完成")
    private String payComplete;

    /**
     * 结算完成时间
     */
    @ExcelHeader(name = "结算完成时间")
    private String settlementCompleteDate;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态",notNull = true)
    private Status status;

    /**
     * 已合作次数
     */
    @ExcelHeader(name = "已合作次数")
    private Integer cooperationNum;

    /**
     * 供应商信息是否完善
     */
    @ExcelHeader(name = "供应商信息是否完善",notNull = true)
    private String infoPerfecting;

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

    public String getUploadBusinLicense() {
        return uploadBusinLicense;
    }

    public void setUploadBusinLicense(String uploadBusinLicense) {
        this.uploadBusinLicense = uploadBusinLicense;
    }

    public String getUploadQualifition() {
        return uploadQualifition;
    }

    public void setUploadQualifition(String uploadQualifition) {
        this.uploadQualifition = uploadQualifition;
    }

    public String getLevelQualifition() {
        return levelQualifition;
    }

    public void setLevelQualifition(String levelQualifition) {
        this.levelQualifition = levelQualifition;
    }

    public String getDeterCooper() {
        return deterCooper;
    }

    public void setDeterCooper(String deterCooper) {
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

    public String getPayComplete() {
        return payComplete;
    }

    public void setPayComplete(String payComplete) {
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

    public String getInfoPerfecting() {
        return infoPerfecting;
    }

    public void setInfoPerfecting(String infoPerfecting) {
        this.infoPerfecting = infoPerfecting;
    }
}