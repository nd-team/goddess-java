package com.bjike.goddess.foreigntax.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 发票基本登记
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:36 ]
 * @Description: [ 发票基本登记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "foreigntax_invoiceinfo")
public class InvoiceInfo extends BaseEntity {

    /**
     * 票种
     */
    @Column(name = "ticket", columnDefinition = "VARCHAR(255)   COMMENT '票种'")
    private String ticket;

    /**
     * 全部购票员
     */
    @Column(name = "fullTicket", columnDefinition = "VARCHAR(255)   COMMENT '全部购票员'")
    private String fullTicket;

    /**
     * 取票方式
     */
    @Column(name = "ticketWay", columnDefinition = "VARCHAR(255)   COMMENT '取票方式'")
    private String ticketWay;


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