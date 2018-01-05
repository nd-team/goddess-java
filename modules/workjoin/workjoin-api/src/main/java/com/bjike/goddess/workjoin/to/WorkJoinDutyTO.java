package com.bjike.goddess.workjoin.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 工作交接责任义务
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkJoinDutyTO extends BaseTO {

    /**
     * 对象
     */
    private String object;

    /**
     * 责任与义务
     */
    private String duty;


    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}