package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 离职名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OutEmployeeTO extends BaseTO {

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
    @NotNull(groups = {EDIT.class}, message = "是否继续购买不能为空")
    private boolean isAgain;

    /**
     * 意见
     */
    @NotNull(groups = {EDIT.class}, message = "是否继续购买不能为空")
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

    public boolean getIsAgain() {
        return isAgain;
    }

    public void setIsAgain(boolean isAgain) {
        this.isAgain = isAgain;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}