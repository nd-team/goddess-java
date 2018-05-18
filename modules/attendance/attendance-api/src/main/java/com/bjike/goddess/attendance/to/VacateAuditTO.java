package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 请假审核表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-10 10:56 ]
 * @Description: [ 请假审核表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateAuditTO extends BaseTO {

    /**
     * 审核人
     */
    private String name;

    /**
     * 审核意见
     */
    private String advice;

    /**
     * 审核状态
     */
    private AduitStatus aduitStatus;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }
}