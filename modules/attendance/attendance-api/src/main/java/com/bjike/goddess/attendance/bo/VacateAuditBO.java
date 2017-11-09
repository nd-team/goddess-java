package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 请假审核表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-10 10:56 ]
 * @Description: [ 请假审核表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateAuditBO extends BaseBO {
    /**
     * 审核时间
     */
    private String date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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