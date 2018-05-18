package com.bjike.goddess.shareholdersmanage.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.shareholdersmanage.type.TypeName;


/**
 * 根据股东名链接数据业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 根据股东名链接数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LogoutShareLinkDateVO extends BaseBO {

    /**
     * 股东姓名
     */
    private String shareholderName;
    /**
     * 类型名称
     */
    private TypeName typeName;
    /**
     * 注销股数
     */
    private Integer holdNum;
    /**
     * 出资额
     */
    private Double amount;
    /**
     * 出资方式
     */
    private String capitalWay;

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCapitalWay() {
        return capitalWay;
    }

    public void setCapitalWay(String capitalWay) {
        this.capitalWay = capitalWay;
    }
}