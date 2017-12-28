package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 报销寄件
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销寄件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneReimburseCheckTO extends BaseTO {
    public interface TestAddAndEdit {
    }


    /**
     * 收到发票情况(核对有误描述/付款说明)
     */
    private String receiveTicketCon;




    public String getReceiveTicketCon() {
        return receiveTicketCon;
    }

    public void setReceiveTicketCon(String receiveTicketCon) {
        this.receiveTicketCon = receiveTicketCon;
    }


}