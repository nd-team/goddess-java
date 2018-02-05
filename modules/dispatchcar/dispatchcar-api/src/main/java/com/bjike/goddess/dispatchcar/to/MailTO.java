package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-26 11:10]
 * @Description: [寄件]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MailTO extends BaseTO{

    /**
     * 是否有票据
     */
    @NotNull(message = "是否有票据不能为空", groups = {ADD.class, EDIT.class})
    private Boolean hasBill;

    /**
     * 收件人
     */
    @NotNull(message = "收件人不能为空", groups = {ADD.class, EDIT.class})
    private String sendReceiver;

    /**
     * 寄件时间
     */
    @NotNull(message = "寄件时间不能为空", groups = {ADD.class, EDIT.class})
    private String sendDate;

    /**
     * 地区
     */
    @NotNull(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String sendArea;

    /**
     * 详细地址
     */
    @NotNull(message = "详细地址不能为空", groups = {ADD.class, EDIT.class})
    private String sendAddress;

    /**
     * 寄件备注
     */
    private String sendRemark;


    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getHasBill() {
        return hasBill;
    }

    public void setHasBill(Boolean hasBill) {
        this.hasBill = hasBill;
    }

    public String getSendReceiver() {
        return sendReceiver;
    }

    public void setSendReceiver(String sendReceiver) {
        this.sendReceiver = sendReceiver;
    }

    public String getSendArea() {
        return sendArea;
    }

    public void setSendArea(String sendArea) {
        this.sendArea = sendArea;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }
}
