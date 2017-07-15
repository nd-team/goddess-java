package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 预估应收账款
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 预估应收账款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostForeMoneyTO extends BaseTO {


    /**
     * 预估应收账款
     */
    @NotBlank(groups = {ADD.class,EDIT.class}, message = "预估应收账款名不能为空")
    private String costForeName;
    /**
     * 预估应收账款合计
     */
    private Double forecastAccountSum;

    /**
     * 预估应收账款十日
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "预估应收账款十日不能为空")
    private Double forecastAccountTen;

    /**
     * 预估应收账款十五日
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "预估应收账款十五日不能为空")
    private Double forecastAccountFift;

    /**
     * 预估应收账款二十日
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "预估应收账款二十日不能为空")
    private Double forecastAccountTwtenty;

    /**
     * 预估应收账款三十日
     */
    @NotNull(groups = {ADD.class,EDIT.class}, message = "预估应收账款三十日不能为空")
    private Double forecastAccountThirty;

    public String getCostForeName() {
        return costForeName;
    }

    public void setCostForeName(String costForeName) {
        this.costForeName = costForeName;
    }

    public Double getForecastAccountSum() {
        return forecastAccountSum;
    }

    public void setForecastAccountSum(Double forecastAccountSum) {
        this.forecastAccountSum = forecastAccountSum;
    }

    public Double getForecastAccountTen() {
        return forecastAccountTen;
    }

    public void setForecastAccountTen(Double forecastAccountTen) {
        this.forecastAccountTen = forecastAccountTen;
    }

    public Double getForecastAccountFift() {
        return forecastAccountFift;
    }

    public void setForecastAccountFift(Double forecastAccountFift) {
        this.forecastAccountFift = forecastAccountFift;
    }

    public Double getForecastAccountTwtenty() {
        return forecastAccountTwtenty;
    }

    public void setForecastAccountTwtenty(Double forecastAccountTwtenty) {
        this.forecastAccountTwtenty = forecastAccountTwtenty;
    }

    public Double getForecastAccountThirty() {
        return forecastAccountThirty;
    }

    public void setForecastAccountThirty(Double forecastAccountThirty) {
        this.forecastAccountThirty = forecastAccountThirty;
    }
}