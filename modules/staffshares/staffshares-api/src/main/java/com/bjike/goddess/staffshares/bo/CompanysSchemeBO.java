package com.bjike.goddess.staffshares.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 公司干股交易情况表业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-11 04:38 ]
 * @Description: [ 公司干股交易情况表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanysSchemeBO extends BaseBO {

    /**
     * 方案代码
     */
    private String code;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 出售/发行人
     */
    private String publisher;

    /**
     * 出售/发行数量
     */
    private int number;

    /**
     * 出售/发行价格
     */
    private Double price;

    /**
     * 出售/发行时间
     */
    private LocalDate time;

    /**
     * 已交易数量
     */
    private int quantityNum;

    /**
     * 未交易数量
     */
    private int sharesNum;

    /**
     * 交易收入额
     */
    private Double money;

    /**
     * 状态
     */
    private String status;


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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public int getQuantityNum() {
        return quantityNum;
    }

    public void setQuantityNum(int quantityNum) {
        this.quantityNum = quantityNum;
    }

    public int getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(int sharesNum) {
        this.sharesNum = sharesNum;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}