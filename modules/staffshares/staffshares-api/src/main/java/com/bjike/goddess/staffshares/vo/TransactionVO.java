package com.bjike.goddess.staffshares.vo;

/**
 * 员工持股管理业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 08:59 ]
 * @Description: [ 员工持股管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TransactionVO {
    /**
     * id
     */
    private String id;

    /**
     * 持股人
     */
    private String shareholder;

    /**
     * 总资产
     */
    private Double totalCapital;

    /**
     * 总收益
     */
    private Double totalEquity;

    /**
     * 本月收益
     */
    private Double equity;

    /**
     * 持股数
     */
    private int sharesNum;

    /**
     * 持仓状态
     */
    private String status;

    /**
     * 是否为干股代表
     */
    private Boolean deputy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
    }

    public Double getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(Double totalCapital) {
        this.totalCapital = totalCapital;
    }

    public Double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(Double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public int getSharesNum() {
        return sharesNum;
    }

    public void setSharesNum(int sharesNum) {
        this.sharesNum = sharesNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDeputy() {
        return deputy;
    }

    public void setDeputy(Boolean deputy) {
        this.deputy = deputy;
    }
}