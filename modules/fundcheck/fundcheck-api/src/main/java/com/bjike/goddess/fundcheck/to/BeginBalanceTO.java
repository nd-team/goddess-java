package com.bjike.goddess.fundcheck.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 期初余额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BeginBalanceTO extends BaseTO {

    /**
     * 日期
     */
    @NotBlank(message = "日期不能为空",groups = {ADD.class, EDIT.class})
    private String date;

    /**
     * 期初余额
     */
    @NotNull(message = "日期不能为空",groups = {ADD.class, EDIT.class})
    private Double beginBalance;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }
}