package com.bjike.goddess.foreigntax.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 发票领用
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "foreigntax_invoicereceive")
public class InvoiceReceive extends BaseEntity {

    /**
     * 购买发票的时间
     */
    @Column(name = "buyInvoiceTime", columnDefinition = "DATE   COMMENT '购买发票的时间'")
    private LocalDate buyInvoiceTime;

    /**
     * 购票员
     */
    @Column(name = "ticketAgent", columnDefinition = "VARCHAR(255)   COMMENT '购票员'")
    private String ticketAgent;

    /**
     * 发票的领用登记
     */
    @Column(name = "register", columnDefinition = "VARCHAR(255)   COMMENT '发票的领用登记'")
    private String register;

    /**
     * 取票方式
     */
    @Column(name = "ticketWay", columnDefinition = "VARCHAR(255)   COMMENT '取票方式'")
    private String ticketWay;


    public LocalDate getBuyInvoiceTime() {
        return buyInvoiceTime;
    }

    public void setBuyInvoiceTime(LocalDate buyInvoiceTime) {
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