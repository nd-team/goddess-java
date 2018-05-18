package com.bjike.goddess.foreigntax.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 发票领用业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvoiceReceiveBO extends BaseBO {

    /**
     * 购买发票的时间
     */
    private String buyInvoiceTime;

    /**
     * 购票员
     */
    private String ticketAgent;

    /**
     * 发票的领用登记
     */
    private String register;

    /**
     * 取票方式
     */
    private String ticketWay;


    public String getBuyInvoiceTime() {
        return buyInvoiceTime;
    }

    public void setBuyInvoiceTime(String buyInvoiceTime) {
        this.buyInvoiceTime = buyInvoiceTime;
    }

    public String getTicketAgent() {
        return ticketAgent;
    }

    public void setTicketAgent(String ticketAgent) {
        this.ticketAgent = ticketAgent;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getTicketWay() {
        return ticketWay;
    }

    public void setTicketWay(String ticketWay) {
        this.ticketWay = ticketWay;
    }
}