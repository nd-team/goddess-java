package com.bjike.goddess.checkfunds.vo;

/**
 * 余额调节表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:11 ]
 * @Description: [ 余额调节表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RemainAdjustVO {

    /**
     * id
     */
    private String id;
    /**
     * 资金流水项目
     */
    private String moneyWaterProject;

    /**
     * 资金流水金额
     */
    private Double moneyWaterSum;

    /**
     * 银行流水项目
     */
    private String bankWaterProject;

    /**
     * 银行流水金额
     */
    private Double bankWaterSum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMoneyWaterProject() {
        return moneyWaterProject;
    }

    public void setMoneyWaterProject(String moneyWaterProject) {
        this.moneyWaterProject = moneyWaterProject;
    }

    public Double getMoneyWaterSum() {
        return moneyWaterSum;
    }

    public void setMoneyWaterSum(Double moneyWaterSum) {
        this.moneyWaterSum = moneyWaterSum;
    }

    public String getBankWaterProject() {
        return bankWaterProject;
    }

    public void setBankWaterProject(String bankWaterProject) {
        this.bankWaterProject = bankWaterProject;
    }

    public Double getBankWaterSum() {
        return bankWaterSum;
    }

    public void setBankWaterSum(Double bankWaterSum) {
        this.bankWaterSum = bankWaterSum;
    }
}