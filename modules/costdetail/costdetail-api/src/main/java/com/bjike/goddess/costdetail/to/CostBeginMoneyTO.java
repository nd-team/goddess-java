package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 初期余额
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 初期余额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostBeginMoneyTO extends BaseTO {

    /**
     * 初期余额
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "初期余额名不能为空")
    private String name;

    /**
     * 初期余额合计
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "初期余额合计不能为空")
    private Double beginBalanceSum;

    /**
     * 初期余额十日
     */
    private Double beginBalanceTen;

    /**
     * 初期余额十五日
     */
    private Double beginBalanceFift;

    /**
     * 初期余额二十日
     */
    private Double beginBalanceTwtenty;

    /**
     * 初期余额三十日
     */
    private Double beginBalanceThirty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBeginBalanceSum() {
        return beginBalanceSum;
    }

    public void setBeginBalanceSum(Double beginBalanceSum) {
        this.beginBalanceSum = beginBalanceSum;
    }

    public Double getBeginBalanceTen() {
        return beginBalanceTen;
    }

    public void setBeginBalanceTen(Double beginBalanceTen) {
        this.beginBalanceTen = beginBalanceTen;
    }

    public Double getBeginBalanceFift() {
        return beginBalanceFift;
    }

    public void setBeginBalanceFift(Double beginBalanceFift) {
        this.beginBalanceFift = beginBalanceFift;
    }

    public Double getBeginBalanceTwtenty() {
        return beginBalanceTwtenty;
    }

    public void setBeginBalanceTwtenty(Double beginBalanceTwtenty) {
        this.beginBalanceTwtenty = beginBalanceTwtenty;
    }

    public Double getBeginBalanceThirty() {
        return beginBalanceThirty;
    }

    public void setBeginBalanceThirty(Double beginBalanceThirty) {
        this.beginBalanceThirty = beginBalanceThirty;
    }
}