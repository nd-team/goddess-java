package com.bjike.goddess.voucher.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

public class VoucherInformationBO extends BaseBO {
    /**
     * 公司
     */
    private String company;
    /**
     * 时间
     */
    private String time;
    /**
     * 附件
     */
    private String attachment;
    /**
     * 会计主管
     */
    private String accountingSupervisor;
    /**
     * 制作人
     */
    private String producer;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAccountingSupervisor() {
        return accountingSupervisor;
    }

    public void setAccountingSupervisor(String accountingSupervisor) {
        this.accountingSupervisor = accountingSupervisor;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
