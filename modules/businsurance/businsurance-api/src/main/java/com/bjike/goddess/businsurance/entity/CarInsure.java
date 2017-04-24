package com.bjike.goddess.businsurance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 车险信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 11:00 ]
 * @Description: [ 车险信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businsurance_carinsure")
public class CarInsure extends BaseEntity {

    /**
     * 投保单号
     */
    @Column(name = "insureNumber",  columnDefinition = "VARCHAR(255)   COMMENT '投保单号'")
    private String insureNumber;

    /**
     * 地址
     */
    @Column(name = "addr",  columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String addr;

    /**
     * 保单起始时间
     */
    @Column(name = "startDate",  columnDefinition = "DATE  COMMENT '保单起始时间'")
    private LocalDate startDate;

    /**
     * 保单结束时间
     */
    @Column(name = "endDate",  columnDefinition = "DATE   COMMENT '保单结束时间'")
    private LocalDate endDate;

    /**
     * 保险费总金额
     */
    @Column(name = "totalFee",  columnDefinition = "DECIMAL(10,2)   COMMENT '保险费总金额'")
    private Double totalFee;

    /**
     * 缴费方式
     */
    @Column(name = "payWay",  columnDefinition = "VARCHAR(255)   COMMENT '缴费方式'")
    private String payWay;

    /**
     * 保险合同争议解决方式
     */
    @Column(name = "argueWay",  columnDefinition = "VARCHAR(255)   COMMENT '保险合同争议解决方式'")
    private String argueWay;

    /**
     * 被保险人
     */
    @Column(name = "insurer",  columnDefinition = "VARCHAR(255)   COMMENT '被保险人'")
    private String insurer;

    /**
     * 被保险人身份证号码
     */
    @Column(name = "insureIdCard",  columnDefinition = "VARCHAR(255)   COMMENT '被保险人身份证号码'")
    private String insureIdCard;

    /**
     * 投保地区
     */
    @Column(name = "insureAddr",  columnDefinition = "VARCHAR(255)   COMMENT '投保地区'")
    private String insureAddr;

    /**
     * 联系电话
     */
    @Column(name = "tel",  columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String tel;

    /**
     * 车牌号码
     */
    @Column(name = "carNumber",  columnDefinition = "VARCHAR(255)   COMMENT '车牌号码'")
    private String carNumber;

    /**
     * 品牌厂名
     */
    @Column(name = "brand",  columnDefinition = "VARCHAR(255)   COMMENT '品牌厂名'")
    private String brand;

    /**
     * 车价选择
     */
    @Column(name = "priceChoice",  columnDefinition = "VARCHAR(255)   COMMENT '车价选择'")
    private String priceChoice;

    /**
     * 所属性质
     */
    @Column(name = "ownNature",  columnDefinition = "VARCHAR(255)   COMMENT '所属性质'")
    private String ownNature;

    /**
     * 使用性质
     */
    @Column(name = "useNature",  columnDefinition = "VARCHAR(255)   COMMENT '使用性质'")
    private String useNature;

    /**
     * 机动车种类
     */
    @Column(name = "carType",  columnDefinition = "VARCHAR(255)   COMMENT '机动车种类'")
    private String carType;

    /**
     * 发动机号码
     */
    @Column(name = "engineNumber",  columnDefinition = "VARCHAR(255)   COMMENT '发动机号码'")
    private String engineNumber;

    /**
     * 是否为过户车
     */
    @Column(name = "transferCar",  columnDefinition = "VARCHAR(255)   COMMENT '是否为过户车'")
    private String transferCar;

    /**
     * 识别代码(车驾号)
     */
    @Column(name = "identityCode",  columnDefinition = "VARCHAR(255)   COMMENT '识别代码(车驾号)'")
    private String identityCode;

    /**
     * 核定载客
     */
    @Column(name = "approvePassenger",  columnDefinition = "VARCHAR(255)   COMMENT '核定载客'")
    private String approvePassenger;

    /**
     * 核定载质量
     */
    @Column(name = "approveLoad",  columnDefinition = "VARCHAR(255)   COMMENT '核定载质量'")
    private String approveLoad;

    /**
     * 排量
     */
    @Column(name = "outputVolume",  columnDefinition = "VARCHAR(255)   COMMENT '排量'")
    private String outputVolume;

    /**
     * 功率
     */
    @Column(name = "power",  columnDefinition = "VARCHAR(255)   COMMENT '功率'")
    private String power;

    /**
     * 车辆初登日期
     */
    @Column(name = "carInitialDate",  columnDefinition = "DATE  COMMENT '车辆初登日期'")
    private LocalDate carInitialDate;

    /**
     * 承包险种
     */
    @Column(name = "insureType",  columnDefinition = "VARCHAR(255)   COMMENT '承包险种'")
    private String insureType;

    /**
     * 是否不计免陪
     */
    @Column(name = "orNotFee",  columnDefinition = "VARCHAR(255)   COMMENT '是否不计免陪'")
    private String orNotFee;

    /**
     * 费率浮动
     */
    @Column(name = "rateFloat",  columnDefinition = "VARCHAR(255)   COMMENT '费率浮动'")
    private String rateFloat;

    /**
     * 保险金额/责任限额
     */
    @Column(name = "insureLimitFee",  columnDefinition = "DECIMAL(10,2)   COMMENT '保险金额/责任限额'")
    private Double insureLimitFee;

    /**
     * 保险费(元)
     */
    @Column(name = "insureFee",  columnDefinition = "DECIMAL(10,2)   COMMENT '保险费(元)'")
    private Double insureFee;

    /**
     * 保险费合计
     */
    @Column(name = "insureTotalFee",  columnDefinition = "DECIMAL(10,2)   COMMENT '保险费合计'")
    private Double insureTotalFee;

    /**
     * 内容明细
     */
    @Column(name = "contentDetail",  columnDefinition = "VARCHAR(255)   COMMENT '内容明细'")
    private String contentDetail;

    /**
     * 机构名称
     */
    @Column(name = "organName",  columnDefinition = "VARCHAR(255)   COMMENT '机构名称'")
    private String organName;

    /**
     * 经办人名字
     */
    @Column(name = "operateName",  columnDefinition = "VARCHAR(255)   COMMENT '经办人名字'")
    private String operateName;

    /**
     * 机构地址
     */
    @Column(name = "organAddr",  columnDefinition = "VARCHAR(255)   COMMENT '机构地址'")
    private String organAddr;

    /**
     * 机构联系方式
     */
    @Column(name = "organContact",  columnDefinition = "VARCHAR(255)   COMMENT '机构联系方式'")
    private String organContact;

    /**
     * 网址
     */
    @Column(name = "interAddr",  columnDefinition = "VARCHAR(255)   COMMENT '网址'")
    private String interAddr;

    /**
     * 邮政编码
     */
    @Column(name = "postCode",  columnDefinition = "VARCHAR(255)   COMMENT '邮政编码'")
    private String postCode;

    /**
     * 签单日期
     */
    @Column(name = "signDate",  columnDefinition = "DATE   COMMENT '签单日期'")
    private LocalDate signDate;

    /**
     * 附件
     */
    @Column(name = "attachFile",  columnDefinition = "VARCHAR(255)   COMMENT '附件'")
    private String attachFile;

    /**
     * 是否续签(是/否)
     */
    @Column(name = "resign",  columnDefinition = "VARCHAR(255)   COMMENT '是否续签(是/否)'")
    private String resign;

    /**
     * 意外险记录id
     */
    @Column(name = "insureRecordId",  columnDefinition = "VARCHAR(255)   COMMENT '意外险记录id'")
    private String insureRecordId;

    public String getInsureNumber() {
        return insureNumber;
    }

    public void setInsureNumber(String insureNumber) {
        this.insureNumber = insureNumber;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getArgueWay() {
        return argueWay;
    }

    public void setArgueWay(String argueWay) {
        this.argueWay = argueWay;
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    public String getInsureIdCard() {
        return insureIdCard;
    }

    public void setInsureIdCard(String insureIdCard) {
        this.insureIdCard = insureIdCard;
    }

    public String getInsureAddr() {
        return insureAddr;
    }

    public void setInsureAddr(String insureAddr) {
        this.insureAddr = insureAddr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPriceChoice() {
        return priceChoice;
    }

    public void setPriceChoice(String priceChoice) {
        this.priceChoice = priceChoice;
    }

    public String getOwnNature() {
        return ownNature;
    }

    public void setOwnNature(String ownNature) {
        this.ownNature = ownNature;
    }

    public String getUseNature() {
        return useNature;
    }

    public void setUseNature(String useNature) {
        this.useNature = useNature;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getTransferCar() {
        return transferCar;
    }

    public void setTransferCar(String transferCar) {
        this.transferCar = transferCar;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getApprovePassenger() {
        return approvePassenger;
    }

    public void setApprovePassenger(String approvePassenger) {
        this.approvePassenger = approvePassenger;
    }

    public String getApproveLoad() {
        return approveLoad;
    }

    public void setApproveLoad(String approveLoad) {
        this.approveLoad = approveLoad;
    }

    public String getOutputVolume() {
        return outputVolume;
    }

    public void setOutputVolume(String outputVolume) {
        this.outputVolume = outputVolume;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public LocalDate getCarInitialDate() {
        return carInitialDate;
    }

    public void setCarInitialDate(LocalDate carInitialDate) {
        this.carInitialDate = carInitialDate;
    }

    public String getInsureType() {
        return insureType;
    }

    public void setInsureType(String insureType) {
        this.insureType = insureType;
    }

    public String getOrNotFee() {
        return orNotFee;
    }

    public void setOrNotFee(String orNotFee) {
        this.orNotFee = orNotFee;
    }

    public String getRateFloat() {
        return rateFloat;
    }

    public void setRateFloat(String rateFloat) {
        this.rateFloat = rateFloat;
    }

    public Double getInsureLimitFee() {
        return insureLimitFee;
    }

    public void setInsureLimitFee(Double insureLimitFee) {
        this.insureLimitFee = insureLimitFee;
    }

    public Double getInsureFee() {
        return insureFee;
    }

    public void setInsureFee(Double insureFee) {
        this.insureFee = insureFee;
    }

    public Double getInsureTotalFee() {
        return insureTotalFee;
    }

    public void setInsureTotalFee(Double insureTotalFee) {
        this.insureTotalFee = insureTotalFee;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOrganAddr() {
        return organAddr;
    }

    public void setOrganAddr(String organAddr) {
        this.organAddr = organAddr;
    }

    public String getOrganContact() {
        return organContact;
    }

    public void setOrganContact(String organContact) {
        this.organContact = organContact;
    }

    public String getInterAddr() {
        return interAddr;
    }

    public void setInterAddr(String interAddr) {
        this.interAddr = interAddr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getResign() {
        return resign;
    }

    public void setResign(String resign) {
        this.resign = resign;
    }

    public String getInsureRecordId() {
        return insureRecordId;
    }

    public void setInsureRecordId(String insureRecordId) {
        this.insureRecordId = insureRecordId;
    }
}