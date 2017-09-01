package com.bjike.goddess.shareholdersmanage.vo;

/**
 * 新增股权表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:17 ]
 * @Description: [ 新增股权表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NewEquityVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 股东姓名
     */
    private String shareholderName;

    /**
     * 股权类型
     */
    private String equityType;

    /**
     * 新增股数
     */
    private Integer newHoldNum;

    /**
     * 每股价格/元
     */
    private Double perSharePrice;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 新增时间
     */
    private String newSaveDate;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

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

    public Integer getNewHoldNum() {
        return newHoldNum;
    }

    public void setNewHoldNum(Integer newHoldNum) {
        this.newHoldNum = newHoldNum;
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

    public String getNewSaveDate() {
        return newSaveDate;
    }

    public void setNewSaveDate(String newSaveDate) {
        this.newSaveDate = newSaveDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}