package com.bjike.goddess.intromanage.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-08-21 15:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BussinesVO {
    /**
     * 注册资金
     */
    private String registerMoney;
    /**
     * 员工数量
     */
    private String staffNo;

    public String getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String registerMoney) {
        this.registerMoney = registerMoney;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }
}
