package com.bjike.goddess.staffshares.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 买入记录表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:09 ]
 * @Description: [ 买入记录表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyscheduleTO extends BaseTO {

    /**
     * 持股人
     */
    private String shareholder;

    /**
     * 方案代码
     */
    private String code;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 购入股数
     */
    @NotNull(message = "购入股数不能为空", groups = {EDIT.class})
    private Long purchaseNum;

    /**
     * 买入价格
     */
    private Double buyPrice;

    /**
     * 发行价格
     */
    private Double price;

    /**
     * 买入金额
     */
    @NotNull(message = "买入金额不能为空", groups = {EDIT.class})
    private Double totalBuyPrice;

    /**
     * 发行金额
     */
    private Double totalPrice;

    /**
     * 出售人
     */
    private String sell;

    /**
     * 交易时间
     */
    private String time;


    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Long purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public void setTotalBuyPrice(Double totalBuyPrice) {
        this.totalBuyPrice = totalBuyPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}