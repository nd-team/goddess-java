package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请借款寄件
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款寄件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneLendSendTO extends BaseTO {

    public interface TESTRETURNSEND{}


    /**
     * 收件人
     */
    private String sendRecevier;

    /**
     * 寄件日期
     */
    @NotBlank(groups = {PhoneLendSendTO.TESTRETURNSEND.class},message ="寄件日期不能为空" )
    private String sendDate;

    /**
     * 寄件情况（寄件的时候的备注）
     */
    private String sendCondition;

    /**
     * 收件地区(寄件的时候填的地区)
     */
    private String receiveArea;

    /**
     * 收件地址(详细地址)
     */
    @NotBlank(groups = {PhoneLendSendTO.TESTRETURNSEND.class},message ="收件地址详细地址不能为空" )
    private String receiveAddr;

    /**
     * 单据数量
     */
    @NotNull(groups = {PhoneLendSendTO.TESTRETURNSEND.class},message ="单据数量不能为空,且为数字" )
    private Double documentQuantity;

    public String getSendRecevier() {
        return sendRecevier;
    }

    public void setSendRecevier(String sendRecevier) {
        this.sendRecevier = sendRecevier;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendCondition() {
        return sendCondition;
    }

    public void setSendCondition(String sendCondition) {
        this.sendCondition = sendCondition;
    }

    public String getReceiveArea() {
        return receiveArea;
    }

    public void setReceiveArea(String receiveArea) {
        this.receiveArea = receiveArea;
    }

    public String getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public Double getDocumentQuantity() {
        return documentQuantity;
    }

    public void setDocumentQuantity(Double documentQuantity) {
        this.documentQuantity = documentQuantity;
    }
}