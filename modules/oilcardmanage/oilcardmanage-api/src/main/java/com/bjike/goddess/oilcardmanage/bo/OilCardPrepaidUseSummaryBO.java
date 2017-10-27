package com.bjike.goddess.oilcardmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 图形化业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-16 09:21 ]
* @Description:	[ 图形化业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class OilCardPrepaidUseSummaryBO extends BaseBO {

    /**
     * 油卡数
     */
    private Integer  oilCardNumber;

    /**
     * 更新油卡余额信息次数
     */
    private Integer  updateOilcardBalanceNumber;

    /**
     * 充值次数
     */
    private Integer  prepaidNumber;

    /**
     * 充值总金额
     */
    private Double  prepaidTotalMoney;

    /**
     * 加油总金额
     */
    private Double  addOilTotalMoney;

    /**
     * 余额
     */
    private Double  balance;

    public Integer getOilCardNumber() {
        return oilCardNumber;
    }

    public void setOilCardNumber(Integer oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public Integer getUpdateOilcardBalanceNumber() {
        return updateOilcardBalanceNumber;
    }

    public void setUpdateOilcardBalanceNumber(Integer updateOilcardBalanceNumber) {
        this.updateOilcardBalanceNumber = updateOilcardBalanceNumber;
    }

    public Integer getPrepaidNumber() {
        return prepaidNumber;
    }

    public void setPrepaidNumber(Integer prepaidNumber) {
        this.prepaidNumber = prepaidNumber;
    }

    public Double getPrepaidTotalMoney() {
        return prepaidTotalMoney;
    }

    public void setPrepaidTotalMoney(Double prepaidTotalMoney) {
        this.prepaidTotalMoney = prepaidTotalMoney;
    }

    public Double getAddOilTotalMoney() {
        return addOilTotalMoney;
    }

    public void setAddOilTotalMoney(Double addOilTotalMoney) {
        this.addOilTotalMoney = addOilTotalMoney;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}