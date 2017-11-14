package com.bjike.goddess.employeecontract.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;
/**
* 员工合同管理汇总业务传输对象
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-10 03:22 ]
* @Description:	[ 员工合同管理汇总业务传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class ContractCollectBO extends BaseBO {
    /**
     * 项目组/部门
     */
    private String  department;

    /**
     * 注册用户名
     */
    private Integer  enrollUserNumber;

    /**
     * 在职时长=0-1个月数
     */
    private Integer  onJobNumber;

    /**
     * 劳动合同已签订数
     */
    private Integer  contractAlreadySignNumber;

    /**
     * 待签订
     */
    private Integer  waitSignContract;

    /**
     * 已到期未续签
     */
    private Integer  alreadyDeadLineNotRenew;

    /**
     * 离职人数
     */
    private Integer  dimissionNumber;

    /**
     * 解除数
     */
    private Integer  relieveNumber;

    /**
     * 第一次续签是否需要合同变更数
     */
    private Integer  firstRenewIfNeedContract;

    /**
     * 第二次续签是否需要合同变更数
     */
    private Integer  secondRenewIfNeedContract;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getEnrollUserNumber() {
        return enrollUserNumber;
    }

    public void setEnrollUserNumber(Integer enrollUserNumber) {
        this.enrollUserNumber = enrollUserNumber;
    }

    public Integer getOnJobNumber() {
        return onJobNumber;
    }

    public void setOnJobNumber(Integer onJobNumber) {
        this.onJobNumber = onJobNumber;
    }

    public Integer getContractAlreadySignNumber() {
        return contractAlreadySignNumber;
    }

    public void setContractAlreadySignNumber(Integer contractAlreadySignNumber) {
        this.contractAlreadySignNumber = contractAlreadySignNumber;
    }

    public Integer getWaitSignContract() {
        return waitSignContract;
    }

    public void setWaitSignContract(Integer waitSignContract) {
        this.waitSignContract = waitSignContract;
    }

    public Integer getAlreadyDeadLineNotRenew() {
        return alreadyDeadLineNotRenew;
    }

    public void setAlreadyDeadLineNotRenew(Integer alreadyDeadLineNotRenew) {
        this.alreadyDeadLineNotRenew = alreadyDeadLineNotRenew;
    }

    public Integer getDimissionNumber() {
        return dimissionNumber;
    }

    public void setDimissionNumber(Integer dimissionNumber) {
        this.dimissionNumber = dimissionNumber;
    }

    public Integer getRelieveNumber() {
        return relieveNumber;
    }

    public void setRelieveNumber(Integer relieveNumber) {
        this.relieveNumber = relieveNumber;
    }

    public Integer getFirstRenewIfNeedContract() {
        return firstRenewIfNeedContract;
    }

    public void setFirstRenewIfNeedContract(Integer firstRenewIfNeedContract) {
        this.firstRenewIfNeedContract = firstRenewIfNeedContract;
    }

    public Integer getSecondRenewIfNeedContract() {
        return secondRenewIfNeedContract;
    }

    public void setSecondRenewIfNeedContract(Integer secondRenewIfNeedContract) {
        this.secondRenewIfNeedContract = secondRenewIfNeedContract;
    }
}