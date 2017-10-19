package com.bjike.goddess.foreigntax.vo;

/**
 * 发票领用表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvoiceReceiveVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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