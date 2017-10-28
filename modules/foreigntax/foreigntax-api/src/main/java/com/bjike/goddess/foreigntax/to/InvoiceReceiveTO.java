package com.bjike.goddess.foreigntax.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 发票领用
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvoiceReceiveTO extends BaseTO {

    /**
     * 购买发票的时间
     */
    @NotBlank(message = "购买发票的时间不能为空",groups = {ADD.class, EDIT.class})
    private String buyInvoiceTime;

    /**
     * 购票员
     */
    @NotBlank(message = "购票员不能为空",groups = {ADD.class, EDIT.class})
    private String ticketAgent;

    /**
     * 发票的领用登记
     */
    @NotBlank(message = "发票的领用登记不能为空",groups = {ADD.class, EDIT.class})
    private String register;

    /**
     * 取票方式
     */
    @NotBlank(message = "取票方式不能为空",groups = {ADD.class, EDIT.class})
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