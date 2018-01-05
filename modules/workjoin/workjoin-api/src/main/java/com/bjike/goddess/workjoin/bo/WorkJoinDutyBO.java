package com.bjike.goddess.workjoin.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 工作交接责任义务业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkJoinDutyBO extends BaseBO {

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