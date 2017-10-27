package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.financeinit.enums.ScaleShape;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.xml.ws.soap.Addressing;

/**
 * 往来单位
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WithUnitTO extends BaseTO {

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空",groups = {ADD.class, EDIT.class})
    private String companyName;

    /**
     * 税号
     */
    @NotBlank(message = "税号不能为空",groups = {ADD.class, EDIT.class})
    private String ein;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空",groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空",groups = {ADD.class, EDIT.class})
    private String address;

    /**
     * 开户银行
     */
    @NotBlank(message = "开户银行不能为空",groups = {ADD.class, EDIT.class})
    private String bank;

    /**
     * 银行账号
     */
    @NotBlank(message = "银行账号不能为空",groups = {ADD.class, EDIT.class})
    private String account;

    /**
     * 公司规模形式
     */
    @NotNull(message = "公司规模形式不能为空",groups = {ADD.class, EDIT.class})
    private ScaleShape scaleShape;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public ScaleShape getScaleShape() {
        return scaleShape;
    }

    public void setScaleShape(ScaleShape scaleShape) {
        this.scaleShape = scaleShape;
    }
}