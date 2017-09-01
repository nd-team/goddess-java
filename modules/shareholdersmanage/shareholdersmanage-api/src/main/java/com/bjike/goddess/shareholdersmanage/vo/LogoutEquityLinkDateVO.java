package com.bjike.goddess.shareholdersmanage.vo;


/**
 * 根据股东名链接数据业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 根据股东名链接数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LogoutEquityLinkDateVO{

    /**
     * 股东姓名
     */
    private String shareholderName;
    /**
     * 股权类型
     */
    private String equityType;
    /**
     * 注销股数/万
     */
    private Integer holdNum;
    /**
     * 每股价格/元
     */
    private Double perSharePrice;
    /**
     * 金额
     */
    private Double amount;

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public Double getPerSharePrice() {
        return perSharePrice;
    }

    public void setPerSharePrice(Double perSharePrice) {
        this.perSharePrice = perSharePrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}