package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "设备名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 清单
     */
    @NotNull(message = "清单不能为空", groups = {ADD.class, EDIT.class})
    private String fictitious;

    /**
     * 真实/虚拟
     */
    @NotNull(message = "真实/虚拟不能为空", groups = {ADD.class, EDIT.class})
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