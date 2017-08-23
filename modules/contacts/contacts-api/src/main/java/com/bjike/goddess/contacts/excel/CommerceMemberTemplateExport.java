package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;



/**
 * 商务会员卡
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommerceMemberTemplateExport extends BaseEntity {

    /**
     * 开卡名称
     */
    @ExcelHeader(name = "开卡名称" , notNull = true)
    private String name;

    /**
     * 卡号
     */
    @ExcelHeader(name = "卡号" , notNull = true)
    private String number;

    /**
     * 开卡人联系方式
     */
    @ExcelHeader(name = "开卡人联系方式" , notNull = true)
    private String phone;

    /**
     * 用途
     */
    @ExcelHeader(name = "用途" , notNull = false)
    private String purpose;

    /**
     * 类别
     */
    @ExcelHeader(name = "类别" , notNull = false)
    private String type;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注" , notNull = false)
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