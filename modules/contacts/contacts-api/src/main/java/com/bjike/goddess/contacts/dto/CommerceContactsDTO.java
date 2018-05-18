package com.bjike.goddess.contacts.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 商务通讯录数据传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 06:04 ]
 * @Description: [ 商务通讯录数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommerceContactsDTO extends BaseDTO {

    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 手机号
     */
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}