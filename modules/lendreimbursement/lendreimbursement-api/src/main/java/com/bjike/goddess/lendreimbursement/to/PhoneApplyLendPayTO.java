package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.lendreimbursement.enums.LendStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请借款
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneApplyLendPayTO extends BaseTO {

    public interface TESTPAY{}


    /**
     * 付款日期
     */
    @NotBlank(groups = {PhoneApplyLendPayTO.TESTPAY.class},message ="付款日期不能为空,2017-09-12" )
    private String payDate;

    /**
     * 支付来源(付款方式)
     */
    @NotBlank(groups = {PhoneApplyLendPayTO.TESTPAY.class},message ="支付来源(付款方式)不能为空" )
    private String payOrigin;


    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPayOrigin() {
        return payOrigin;
    }

    public void setPayOrigin(String payOrigin) {
        this.payOrigin = payOrigin;
    }


}