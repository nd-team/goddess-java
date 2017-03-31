package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 设备信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FacilityInformationTO extends BaseTO {

    /**
     * 设备名称
     */
    private String name;

    /**
     * 清单
     */
    private String fictitious;

    /**
     * 真实/虚拟
     */
    private Boolean real;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFictitious() {
        return fictitious;
    }

    public void setFictitious(String fictitious) {
        this.fictitious = fictitious;
    }

    public Boolean getReal() {
        return real;
    }

    public void setReal(Boolean real) {
        this.real = real;
    }
}