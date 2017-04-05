package com.bjike.goddess.contacts.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 商务会员卡
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommerceMemberTO extends BaseTO {

    /**
     * 开卡名称
     */
    @NotNull(message = "开卡名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 卡号
     */
    @NotNull(message = "卡号不能为空", groups = {ADD.class, EDIT.class})
    private String number;

    /**
     * 开卡人联系方式
     */
    @NotNull(message = "开卡人联系方式不能为空", groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 用途
     */
    private String purpose;

    /**
     * 类别
     */
    private String type;

    /**
     * 备注
     */
    private String remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}