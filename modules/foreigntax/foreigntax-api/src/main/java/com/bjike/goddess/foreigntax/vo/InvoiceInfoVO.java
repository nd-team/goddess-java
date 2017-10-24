package com.bjike.goddess.foreigntax.vo;

/**
 * 发票基本登记表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:36 ]
 * @Description: [ 发票基本登记表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvoiceInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 票种
     */
    private String ticket;

    /**
     * 全部购票员
     */
    private String fullTicket;

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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getFullTicket() {
        return fullTicket;
    }

    public void setFullTicket(String fullTicket) {
        this.fullTicket = fullTicket;
    }

    public String getTicketWay() {
        return ticketWay;
    }

    public void setTicketWay(String ticketWay) {
        this.ticketWay = ticketWay;
    }
}