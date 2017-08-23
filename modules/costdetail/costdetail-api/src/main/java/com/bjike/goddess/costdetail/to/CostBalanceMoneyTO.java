package com.bjike.goddess.costdetail.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 按时回款预估结余资金
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 按时回款预估结余资金 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostBalanceMoneyTO extends BaseTO {

    /**
     * 按时回款预估结余资金
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "按时回款预估结余资金名不能为空")
    private String costBalanceName;
    /**
     * 按时回款预估结余资金合计
     */
    private Double forecastBalanceMoSum;

    /**
     * 按时回款预估结余资金十日
     */
    private Double forecastBalanceMoGapTen;

    /**
     * 按时回款预估结余资金十五日
     */
    private Double forecastBalanceMoGapFift;

    /**
     * 按时回款预估结余资金二十日
     */
    private Double forecastBalanceMoTwtenty;

    /**
     * 按时回款预估结余资金三十日
     */
    private Double forecastBalanceMoThirty;

    public String getCostBalanceName() {
        return costBalanceName;
    }

    public void setCostBalanceName(String costBalanceName) {
        this.costBalanceName = costBalanceName;
    }

    public Double getForecastBalanceMoSum() {
        return forecastBalanceMoSum;
    }

    public void setForecastBalanceMoSum(Double forecastBalanceMoSum) {
        this.forecastBalanceMoSum = forecastBalanceMoSum;
    }

    public Double getForecastBalanceMoGapTen() {
        return forecastBalanceMoGapTen;
    }

    public void setForecastBalanceMoGapTen(Double forecastBalanceMoGapTen) {
        this.forecastBalanceMoGapTen = forecastBalanceMoGapTen;
    }

    public Double getForecastBalanceMoGapFift() {
        return forecastBalanceMoGapFift;
    }

    public void setForecastBalanceMoGapFift(Double forecastBalanceMoGapFift) {
        this.forecastBalanceMoGapFift = forecastBalanceMoGapFift;
    }

    public Double getForecastBalanceMoTwtenty() {
        return forecastBalanceMoTwtenty;
    }

    public void setForecastBalanceMoTwtenty(Double forecastBalanceMoTwtenty) {
        this.forecastBalanceMoTwtenty = forecastBalanceMoTwtenty;
    }

    public Double getForecastBalanceMoThirty() {
        return forecastBalanceMoThirty;
    }

    public void setForecastBalanceMoThirty(Double forecastBalanceMoThirty) {
        this.forecastBalanceMoThirty = forecastBalanceMoThirty;
    }
}