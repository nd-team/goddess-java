package com.bjike.goddess.businsurance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 意外险记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 05:43 ]
 * @Description: [ 意外险记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InsureRecordTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 商业险类型
     */
    @NotBlank(groups = InsureRecordTO.TestAdd.class , message = "商业险类型")
    private String insureNumber;

    /**
     * 商业险名称
     */
    private String addr;

    /**
     * 保单生效日
     */
    @NotBlank(groups = InsureRecordTO.TestAdd.class , message = "保单生效日不能为空，格式为年月日")
    private String startDate;

    /**
     * 保单期满日
     */
    @NotBlank(groups = InsureRecordTO.TestAdd.class , message = "保单期满日不能为空，格式为年月日")
    private String endDate;

    /**
     * 投保人名字
     */
    private Double totalFee;

    /**
     * 被保险人名字
     */
    private String payWay;

    /**
     * 险种名称
     */
    private String argueWay;

    /**
     * 组合名称
     */
    private String insurer;

    /**
     * 销售机构名称
     */
    private String insureIdCard;

    /**
     * 销售员名称
     */
    private String insureAddr;

    /**
     * 机构电话
     */
    private String tel;

    /**
     * 合同存储编号
     */
    private String carNumber;

    /**
     * 合同存储路径
     */
    private String brand;

    /**
     * 合同附件
     */
    private String priceChoice;

    /**
     * 是否已续保
     */
    private String ownNature;

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