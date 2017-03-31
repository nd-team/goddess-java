package com.bjike.goddess.qualifications.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 设备信息业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FacilityInformationBO extends BaseBO {

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