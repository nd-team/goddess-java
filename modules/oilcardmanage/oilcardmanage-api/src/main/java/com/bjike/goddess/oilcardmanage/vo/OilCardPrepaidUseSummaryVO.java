package com.bjike.goddess.oilcardmanage.vo;

/**
* 图形化表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-16 09:21 ]
* @Description:	[ 图形化表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class OilCardPrepaidUseSummaryVO {
    /**
     * id
     */
    private String  id;
    /**
     * 地区
     */
    private String  area;

    /**
     * 项目组/部门
     */
    private String  department;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

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