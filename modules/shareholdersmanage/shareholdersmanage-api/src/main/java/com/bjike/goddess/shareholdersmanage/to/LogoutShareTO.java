package com.bjike.goddess.shareholdersmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.shareholdersmanage.type.TypeName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 注销股东
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:55 ]
 * @Description: [ 注销股东 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LogoutShareTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 注销股东姓名
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "注销股东姓名不能为空")
    private String logoutShareName;

    /**
     * 类型名称
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "类型名称不能为空")
    private TypeName typeName;

    /**
     * 注销时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "注销时间不能为空")
    private String logoutDate;

    /**
     * 注销股数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "注销股数不能为空")
    private Integer logoutHold;

    /**
     * 出资额
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "出资额不能为空")
    private Double amount;

    /**
     * 出资方式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
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