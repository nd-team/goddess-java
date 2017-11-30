package com.bjike.goddess.foreigntax.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 发票基本登记
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:36 ]
 * @Description: [ 发票基本登记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvoiceInfoTO extends BaseTO {
    public interface TestAdd{}
    public interface TestEdit{}

    /**
     * 票种
     */
    @NotBlank(message = "票种不能为空",groups = {InvoiceInfoTO.TestAdd.class,InvoiceInfoTO.TestEdit.class})
    private String ticket;

    /**
     * 全部购票员
     */
    @NotBlank(message = "全部购票员不能为空",groups = {InvoiceInfoTO.TestAdd.class,InvoiceInfoTO.TestEdit.class})
    private String fullTicket;

    /**
     * 取票方式
     */
    @NotBlank(message = "取票方式不能为空",groups = {InvoiceInfoTO.TestAdd.class,InvoiceInfoTO.TestEdit.class})
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