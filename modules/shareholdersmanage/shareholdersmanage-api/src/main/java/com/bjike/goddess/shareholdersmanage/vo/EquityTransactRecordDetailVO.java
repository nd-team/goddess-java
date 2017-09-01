package com.bjike.goddess.shareholdersmanage.vo;

/**
 * 股权交易记录详情表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:11 ]
 * @Description: [ 股权交易记录详情表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityTransactRecordDetailVO {

    /**
     * id
     */
    private String id;
    /**
     * 股东姓名
     */
    private String shareholderName;

    /**
     * 交易时间
     */
    private String transactDate;
    /**
     * 持股数量
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

    /**
     * 交易类型
     */
    private String transactType;

    /**
     * 对应交易类型id
     */
    private String transactId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public String getTransactDate() {
        return transactDate;
    }

    public void setTransactDate(String transactDate) {
        this.transactDate = transactDate;
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

    public String getTransactType() {
        return transactType;
    }

    public void setTransactType(String transactType) {
        this.transactType = transactType;
    }

    public String getTransactId() {
        return transactId;
    }

    public void setTransactId(String transactId) {
        this.transactId = transactId;
    }
}