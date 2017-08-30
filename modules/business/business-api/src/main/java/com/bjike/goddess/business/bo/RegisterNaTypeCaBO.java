package com.bjike.goddess.business.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 工商注册业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RegisterNaTypeCaBO extends BaseBO {

    /**
     * 注册公司名称
     */
    private String registerCompanyName;

    /**
     * 注册类型
     */
    private String registerType;

    /**
     * 注册资本
     */
    private String registerCapital;


    public String getRegisterCompanyName() {
        return registerCompanyName;
    }

    public void setRegisterCompanyName(String registerCompanyName) {
        this.registerCompanyName = registerCompanyName;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(String registerCapital) {
        this.registerCapital = registerCapital;
    }
}