package com.bjike.goddess.voucher.vo;

/**
* 科目汇总表现层对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-26 02:42 ]
* @Description:	[ 科目汇总表现层对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class SubjectCollectVO {
    /**
     * id
     */
    private String  id;
    /**
     * 项目
     */
    private String project;

    /**
     * 起初借方余额
     */
    private Double beginBorrowMoney;

    /**
     * 起初贷方余额
     */
    private Double beginLoanMoney;

    /**
     * 本期借方余额
     */
    private Double  currentBorrowMoney;

    /**
     * 本期贷方余额
     */
    private Double  currentLoanMoney;

    /**
     * 期末借方发生额
     */
    private Double  endBorrowMoney;

    /**
     * 期末贷方发生额
     */
    private Double  endLoanMoney;

    /**
     * 本年借方统计
     */
    private Double  currentYearBorrowMoney;

    /**
     * 本年贷方统计
     */
    private Double  currentYearLoanMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getBeginBorrowMoney() {
        return beginBorrowMoney;
    }

    public void setBeginBorrowMoney(Double beginBorrowMoney) {
        this.beginBorrowMoney = beginBorrowMoney;
    }

    public Double getBeginLoanMoney() {
        return beginLoanMoney;
    }

    public void setBeginLoanMoney(Double beginLoanMoney) {
        this.beginLoanMoney = beginLoanMoney;
    }

    public Double getCurrentBorrowMoney() {
        return currentBorrowMoney;
    }

    public void setCurrentBorrowMoney(Double currentBorrowMoney) {
        this.currentBorrowMoney = currentBorrowMoney;
    }

    public Double getCurrentLoanMoney() {
        return currentLoanMoney;
    }

    public void setCurrentLoanMoney(Double currentLoanMoney) {
        this.currentLoanMoney = currentLoanMoney;
    }

    public Double getEndBorrowMoney() {
        return endBorrowMoney;
    }

    public void setEndBorrowMoney(Double endBorrowMoney) {
        this.endBorrowMoney = endBorrowMoney;
    }

    public Double getEndLoanMoney() {
        return endLoanMoney;
    }

    public void setEndLoanMoney(Double endLoanMoney) {
        this.endLoanMoney = endLoanMoney;
    }

    public Double getCurrentYearBorrowMoney() {
        return currentYearBorrowMoney;
    }

    public void setCurrentYearBorrowMoney(Double currentYearBorrowMoney) {
        this.currentYearBorrowMoney = currentYearBorrowMoney;
    }

    public Double getCurrentYearLoanMoney() {
        return currentYearLoanMoney;
    }

    public void setCurrentYearLoanMoney(Double currentYearLoanMoney) {
        this.currentYearLoanMoney = currentYearLoanMoney;
    }
}