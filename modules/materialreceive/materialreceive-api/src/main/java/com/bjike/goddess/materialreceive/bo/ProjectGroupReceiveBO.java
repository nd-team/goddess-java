package com.bjike.goddess.materialreceive.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 项目组领用归还业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectGroupReceiveBO extends BaseBO {

    /**
     * 领用时间
     */
    private String receiveTime;

    /**
     * 物品名称
     */
    private String materialName;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 领用人
     */
    private String recipient;

    /**
     * 发放人
     */
    private String issuer;

    /**
     * 归还时间
     */
    private String returnTime;

    /**
     * 归还人
     */
    private String returnPerson;


    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnPerson() {
        return returnPerson;
    }

    public void setReturnPerson(String returnPerson) {
        this.returnPerson = returnPerson;
    }
}