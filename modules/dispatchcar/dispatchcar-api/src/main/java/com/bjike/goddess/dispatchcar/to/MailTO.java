package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-26 11:10]
 * @Description: [寄件]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MailTO extends BaseTO{
    /**
     * 寄件日期
     */
    private String sendDate;

    /**
     * 存档联是否寄件
     */
    private Boolean ifSendArchiveAl;

    /**
     * 报销联是否寄件
     */
    private Boolean ifSendReimbursementAl;

    /**
     * 过路停车费总额
     */
    private Double totalParking;


    /**
     * 过路停车费小票总数
     */
    private Integer tatalReceipts;

    /**
     * 加油小票是否寄件
     */
    private Boolean ifSendAddOilReceipts;


    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getIfSendArchiveAl() {
        return ifSendArchiveAl;
    }

    public void setIfSendArchiveAl(Boolean ifSendArchiveAl) {
        this.ifSendArchiveAl = ifSendArchiveAl;
    }

    public Boolean getIfSendReimbursementAl() {
        return ifSendReimbursementAl;
    }

    public void setIfSendReimbursementAl(Boolean ifSendReimbursementAl) {
        this.ifSendReimbursementAl = ifSendReimbursementAl;
    }

    public Double getTotalParking() {
        return totalParking;
    }

    public void setTotalParking(Double totalParking) {
        this.totalParking = totalParking;
    }

    public Integer getTatalReceipts() {
        return tatalReceipts;
    }

    public void setTatalReceipts(Integer tatalReceipts) {
        this.tatalReceipts = tatalReceipts;
    }

    public Boolean getIfSendAddOilReceipts() {
        return ifSendAddOilReceipts;
    }

    public void setIfSendAddOilReceipts(Boolean ifSendAddOilReceipts) {
        this.ifSendAddOilReceipts = ifSendAddOilReceipts;
    }
}
