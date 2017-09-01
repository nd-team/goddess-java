package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.shareholdersmanage.type.ShareholderStatus;

/**
 * 股权交易记录
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:05 ]
 * @Description: [ 股权交易记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EquityTransactRecordTO extends BaseTO {

    /**
     * 股东姓名
     */
    private String shareholderName;

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
     * 股权类型
     */
    private String equityType;

    /**
     * 持股比例
     */
    private Double percentage;

    /**
     * 股东状态
     */
    private ShareholderStatus shareholderStatus;


    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
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

    public String getEquityType() {
        return equityType;
    }

    public void setEquityType(String equityType) {
        this.equityType = equityType;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public ShareholderStatus getShareholderStatus() {
        return shareholderStatus;
    }

    public void setShareholderStatus(ShareholderStatus shareholderStatus) {
        this.shareholderStatus = shareholderStatus;
    }
}