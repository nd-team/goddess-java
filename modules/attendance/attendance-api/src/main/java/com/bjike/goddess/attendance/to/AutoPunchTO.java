package com.bjike.goddess.attendance.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 自动打卡
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-06 09:22 ]
 * @Description: [ 自动打卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AutoPunchTO extends BaseTO {

    /**
     * 上班时间
     */
    @NotBlank(groups = ADD.class,message = "上班时间")
    private String goTime;

    /**
     * 下班时间
     */
    @NotBlank(groups = ADD.class,message = "下班时间")
    private String outTime;

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}