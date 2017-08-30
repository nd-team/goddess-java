package com.bjike.goddess.secure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 离职名单业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutEmployeeBO extends BaseBO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 离职时间
     */
    private String endTime;

    /**
     * 是否继续购买
     */
    private Boolean isAgain;

    /**
     * 意见
     */
    private String advice;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(Boolean isAgain) {
        this.isAgain = isAgain;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}