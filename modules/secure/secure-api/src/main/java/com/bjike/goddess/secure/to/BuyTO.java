package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 购买社保人员
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BuyTO extends BaseTO {

    /**
     * 姓名
     */
//    @NotBlank(groups = {EDIT.class},message = "姓名不能为空")
    private String name;

    /**
     * 参保公司
     */
//    @NotBlank(groups = {EDIT.class},message = "参保公司不能为空")
    private String company;

    /**
     * 参保地市
     */
//    @NotBlank(groups = {EDIT.class},message = "参保地市不能为空")
    private String city;

    /**
     * 参保户口
     */
//    @NotBlank(groups = {EDIT.class},message = "参保户口不能为空")
    private String born;

    /**
     * 参保类型
     */
//    @NotBlank(groups = {EDIT.class},message = "参保类型不能为空")
    private String secureType;

    /**
     * 购买方式
     */
//    @NotBlank(groups = {EDIT.class},message = "购买方式不能为空")
    private String payType;

    /**
     * 社保状态
     */
//    @NotBlank(groups = {EDIT.class},message = "社保状态不能为空")
    private String secureStatus;

    /**
     * 审批状态
     */
//    @NotNull(groups = {EDIT.class},message = "审批状态不能为空")
    private boolean examine;


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