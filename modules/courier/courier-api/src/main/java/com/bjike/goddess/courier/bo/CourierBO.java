package com.bjike.goddess.courier.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.courier.enums.AttributeType;
import com.bjike.goddess.courier.enums.Range;

/**
 * 快递收发业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:25 ]
 * @Description: [ 快递收发业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CourierBO extends BaseBO {

    /**
     * 地区
     */
    private String arrival;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 寄件时间
     */
    private String sendTime;

    /**
     * 预计收件时间
     */
    private String receiptTime;

    /**
     * 寄件人
     */
    private String sendPerson;

    /**
     * 寄件地
     */
    private String sendArrival;

    /**
     * (寄)联系方式
     */
    private String sendTel;

    /**
     * 收件地
     */
    private String receiptArrival;

    /**
     * 寄收件范围
     */
    private Range cityRange;

    /**
     * 收件人
     */
    private String receiptPerson;

    /**
     * (收)联系方式
     */
    private String receiptTel;

    /**
     * 物品属性
     */
    private AttributeType attributeType;

    /**
     * 物品名称
     */
    private String itemsName;

    /**
     * 物品数量
     */
    private Integer itemsNum;

    /**
     * 快递公司
     */
    private String courierCompany;

    /**
     * 快递公司联系方式
     */
    private String courierTel;

    /**
     * 快递重量
     */
    private Double courierQuality;

    /**
     * 快递单号
     */
    private String courierNumber;

    /**
     * 快递费总额
     */
    private Double courierSum;

    /**
     * 运费(元)
     */
    private Double freight;

    /**
     * 保险费(元)
     */
    private Double secure;

    /**
     * 总费用
     */
    private Double feeSum;

    /**
     * 余额
     */
    private Double remainingSum;

    /**
     * 是否有发票/收据
     */
    private Boolean isInvoice;

    /**
     * 是否公司支付
     */
    private Boolean isCompanyPay;

    /**
     * 支付人
     */
    private String payPerson;

    /**
     * 是否收件
     */
    private Boolean isReceipt;

    /**
     * 签收人
     */
    private String signPerson;

    /**
     * 收件清单确认
     */
    private Boolean isConfirm;

    /**
     * 备注
     */
    private String description;

    /**
     * 年
     */
    private Integer year;

    /**
     * 月
     */
    private Integer month;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(String receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    public String getSendArrival() {
        return sendArrival;
    }

    public void setSendArrival(String sendArrival) {
        this.sendArrival = sendArrival;
    }

    public String getSendTel() {
        return sendTel;
    }

    public void setSendTel(String sendTel) {
        this.sendTel = sendTel;
    }

    public String getReceiptArrival() {
        return receiptArrival;
    }

    public void setReceiptArrival(String receiptArrival) {
        this.receiptArrival = receiptArrival;
    }

    public Range getCityRange() {
        return cityRange;
    }

    public void setCityRange(Range cityRange) {
        this.cityRange = cityRange;
    }

    public String getReceiptPerson() {
        return receiptPerson;
    }

    public void setReceiptPerson(String receiptPerson) {
        this.receiptPerson = receiptPerson;
    }

    public String getReceiptTel() {
        return receiptTel;
    }

    public void setReceiptTel(String receiptTel) {
        this.receiptTel = receiptTel;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public Integer getItemsNum() {
        return itemsNum;
    }

    public void setItemsNum(Integer itemsNum) {
        this.itemsNum = itemsNum;
    }

    public String getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(String courierCompany) {
        this.courierCompany = courierCompany;
    }

    public String getCourierTel() {
        return courierTel;
    }

    public void setCourierTel(String courierTel) {
        this.courierTel = courierTel;
    }

    public Double getCourierQuality() {
        return courierQuality;
    }

    public void setCourierQuality(Double courierQuality) {
        this.courierQuality = courierQuality;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public Double getCourierSum() {
        return courierSum;
    }

    public void setCourierSum(Double courierSum) {
        this.courierSum = courierSum;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getSecure() {
        return secure;
    }

    public void setSecure(Double secure) {
        this.secure = secure;
    }

    public Double getFeeSum() {
        return feeSum;
    }

    public void setFeeSum(Double feeSum) {
        this.feeSum = feeSum;
    }

    public Double getRemainingSum() {
        return remainingSum;
    }

    public void setRemainingSum(Double remainingSum) {
        this.remainingSum = remainingSum;
    }

    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    public Boolean getIsCompanyPay() {
        return isCompanyPay;
    }

    public void setIsCompanyPay(Boolean isCompanyPay) {
        this.isCompanyPay = isCompanyPay;
    }

    public String getPayPerson() {
        return payPerson;
    }

    public void setPayPerson(String payPerson) {
        this.payPerson = payPerson;
    }

    public Boolean getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(Boolean isReceipt) {
        this.isReceipt = isReceipt;
    }

    public String getSignPerson() {
        return signPerson;
    }

    public void setSignPerson(String signPerson) {
        this.signPerson = signPerson;
    }

    public Boolean getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Boolean isConfirm) {
        this.isConfirm = isConfirm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}