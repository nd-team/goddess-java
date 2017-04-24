package com.bjike.goddess.businsurance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 车险信息管理业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 11:00 ]
 * @Description: [ 车险信息管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CarInsureBO extends BaseBO {

    /**
     * 投保单号
     */
    private String insureNumber;

    /**
     * 地址
     */
    private String addr;

    /**
     * 保单起始时间
     */
    private String startDate;

    /**
     * 保单结束时间
     */
    private String endDate;

    /**
     * 保险费总金额
     */
    private Double totalFee;

    /**
     * 缴费方式
     */
    private String payWay;

    /**
     * 保险合同争议解决方式
     */
    private String argueWay;

    /**
     * 被保险人
     */
    private String insurer;

    /**
     * 被保险人身份证号码
     */
    private String insureIdCard;

    /**
     * 投保地区
     */
    private String insureAddr;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 车牌号码
     */
    private String carNumber;

    /**
     * 品牌厂名
     */
    private String brand;

    /**
     * 车价选择
     */
    private String priceChoice;

    /**
     * 所属性质
     */
    private String ownNature;

    /**
     * 使用性质
     */
    private String useNature;

    /**
     * 机动车种类
     */
    private String carType;

    /**
     * 发动机号码
     */
    private String engineNumber;

    /**
     * 是否为过户车
     */
    private String transferCar;

    /**
     * 识别代码(车驾号)
     */
    private String identityCode;

    /**
     * 核定载客
     */
    private String approvePassenger;

    /**
     * 核定载质量
     */
    private String approveLoad;

    /**
     * 排量
     */
    private String outputVolume;

    /**
     * 功率
     */
    private String power;

    /**
     * 车辆初登日期
     */
    private String carInitialDate;

    /**
     * 承包险种
     */
    private String insureType;

    /**
     * 是否不计免陪
     */
    private String orNotFee;

    /**
     * 费率浮动
     */
    private String rateFloat;

    /**
     * 保险金额/责任限额
     */
    private Double insureLimitFee;

    /**
     * 保险费(元)
     */
    private Double insureFee;

    /**
     * 保险费合计
     */
    private Double insureTotalFee;

    /**
     * 内容明细
     */
    private String contentDetail;

    /**
     * 机构名称
     */
    private String organName;

    /**
     * 经办人名字
     */
    private String operateName;

    /**
     * 机构地址
     */
    private String organAddr;

    /**
     * 机构联系方式
     */
    private String organContact;

    /**
     * 网址
     */
    private String interAddr;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 签单日期
     */
    private String signDate;

    /**
     * 附件
     */
    private String attachFile;

    /**
     * 是否续签(是/否)
     */
    private String resign;

    /**
     * 意外险记录id
     */
    private String insureRecordId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public String getCarInitialDate() {
        return carInitialDate;
    }

    public void setCarInitialDate(String carInitialDate) {
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

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
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