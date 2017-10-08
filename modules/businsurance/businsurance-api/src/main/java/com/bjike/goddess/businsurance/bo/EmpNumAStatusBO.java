package com.bjike.goddess.businsurance.bo;

import com.bjike.goddess.businsurance.enums.BuyCasualtyStatus;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 所有的员工编号和购买意外险状态业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 所有的员工编号和购买意外险状态业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class EmpNumAStatusBO extends BaseBO {

    /**
     * 员工编号
     */
    private String employeeNo;
    /**
     * 停止购买时间
     */
    private String stopBuyTime;
    /**
     * 购买意外险状态
     */
    private BuyCasualtyStatus buyCasualtyStatus;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getStopBuyTime() {
        return stopBuyTime;
    }

    public void setStopBuyTime(String stopBuyTime) {
        this.stopBuyTime = stopBuyTime;
    }

    public BuyCasualtyStatus getBuyCasualtyStatus() {
        return buyCasualtyStatus;
    }

    public void setBuyCasualtyStatus(BuyCasualtyStatus buyCasualtyStatus) {
        this.buyCasualtyStatus = buyCasualtyStatus;
    }
}