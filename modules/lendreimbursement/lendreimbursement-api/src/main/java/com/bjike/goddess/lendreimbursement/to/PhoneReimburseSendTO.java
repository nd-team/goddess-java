package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 报销寄件
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销寄件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneReimburseSendTO extends BaseTO {
    public interface TestAddAndEdit {
    }


    /**
     * 是否有发票(单据)(是/否)
     */
    @NotBlank(groups = {PhoneReimburseSendTO.TestAddAndEdit.class}, message = "是否有发票(单据)不能为空(是/否)")
    private String ticketCondition;



    /**
     * 寄件的收件人
     */
    @NotBlank(groups = {PhoneReimburseSendTO.TestAddAndEdit.class}, message = "收件人不能为空")
    private String sendRecevier;


    /**
     * 寄件日期
     */
    @NotBlank(groups = {PhoneReimburseSendTO.TestAddAndEdit.class}, message = "寄件日期不能为空，格式如：2017-09-25")
    private String sendDate;

    /**
     * 寄件情况
     */
    private String sendCondition;


    /**
     * 收件地区(寄件的时候填的地区)
     */
    private String receiveArea;

    /**
     * 收件地址
     */
    @NotBlank(groups = {PhoneReimburseSendTO.TestAddAndEdit.class}, message = "详细地址不能为空")
    private String receiveAddr;

    public String getTicketCondition() {
        return ticketCondition;
    }

    public void setTicketCondition(String ticketCondition) {
        this.ticketCondition = ticketCondition;
    }

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
}