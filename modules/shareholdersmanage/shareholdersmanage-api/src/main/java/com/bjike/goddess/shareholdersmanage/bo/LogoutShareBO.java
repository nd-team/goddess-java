package com.bjike.goddess.shareholdersmanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.shareholdersmanage.type.TypeName;

/**
 * 注销股东业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:55 ]
 * @Description: [ 注销股东业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LogoutShareBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 注销股东姓名
     */
    private String logoutShareName;

    /**
     * 类型名称
     */
    private TypeName typeName;

    /**
     * 注销时间
     */
    private String logoutDate;

    /**
     * 注销股数
     */
    private Integer logoutHold;

    /**
     * 出资额
     */
    private Double amount;

    /**
     * 出资方式
     */
    private String capitalWay;

    /**
     * 备注
     */
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLogoutShareName() {
        return logoutShareName;
    }

    public void setLogoutShareName(String logoutShareName) {
        this.logoutShareName = logoutShareName;
    }

    public TypeName getTypeName() {
        return typeName;
    }

    public void setTypeName(TypeName typeName) {
        this.typeName = typeName;
    }

    public String getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(String logoutDate) {
        this.logoutDate = logoutDate;
    }

    public Integer getLogoutHold() {
        return logoutHold;
    }

    public void setLogoutHold(Integer logoutHold) {
        this.logoutHold = logoutHold;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}