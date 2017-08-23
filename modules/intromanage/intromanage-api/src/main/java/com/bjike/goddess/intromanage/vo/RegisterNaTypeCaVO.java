package com.bjike.goddess.intromanage.vo;


/**
 * 获取工商注册数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 获取工商注册数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RegisterNaTypeCaVO {

    /**
     * id
     */
    private String id;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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