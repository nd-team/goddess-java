package com.bjike.goddess.oilcardmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 油卡充值业务对象
 * @Author: [Jason]
 * @Date: [17-3-15 上午10:49]
 * @Package:[ com.bjike.goddess.oilcardmanage.bo ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AnalyzeBO extends BaseBO {

    /**
     * 油卡编号
     */
    private String oilCardCode;

    /**
     * 充值金额
     */
    private Double rechargeMoney;

    /**
     * 加油金额
     */
    private Double addOilAmount;

    /**
     * 充值次数
     */
    private Long count;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 余额
     */
    private Double cycleEarlyMoney;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getOilCardCode() {
        return oilCardCode;
    }

    public void setOilCardCode(String oilCardCode) {
        this.oilCardCode = oilCardCode;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Double getAddOilAmount() {
        return addOilAmount;
    }

    public void setAddOilAmount(Double addOilAmount) {
        this.addOilAmount = addOilAmount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getCycleEarlyMoney() {
        return cycleEarlyMoney;
    }

    public void setCycleEarlyMoney(Double cycleEarlyMoney) {
        this.cycleEarlyMoney = cycleEarlyMoney;
    }
}
