package com.bjike.goddess.secure.vo;

/**
 * 购买社保人员表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 参保公司
     */
    private String company;

    /**
     * 参保地市
     */
    private String city;

    /**
     * 参保户口
     */
    private String born;

    /**
     * 参保类型
     */
    private String secureType;

    /**
     * 购买方式
     */
    private String payType;

    /**
     * 社保状态
     */
    private String secureStatus;

    /**
     * 审批状态
     */
    private boolean examine;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getSecureType() {
        return secureType;
    }

    public void setSecureType(String secureType) {
        this.secureType = secureType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSecureStatus() {
        return secureStatus;
    }

    public void setSecureStatus(String secureStatus) {
        this.secureStatus = secureStatus;
    }

    public boolean getExamine() {
        return examine;
    }

    public void setExamine(boolean examine) {
        this.examine = examine;
    }
}