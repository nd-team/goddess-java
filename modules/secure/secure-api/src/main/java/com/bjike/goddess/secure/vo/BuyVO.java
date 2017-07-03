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
    private String bornLocal;

    /**
     * 参保类型
     */
    private String type;

    /**
     * 购买方式
     */
    private String payType;

    /**
     * 社保状态
     */
    private String status;

    /**
     * 审批状态
     */
    private Boolean examine;

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

    public String getBornLocal() {
        return bornLocal;
    }

    public void setBornLocal(String bornLocal) {
        this.bornLocal = bornLocal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isExamine() {
        return examine;
    }

    public void setExamine(Boolean examine) {
        this.examine = examine;
    }
}