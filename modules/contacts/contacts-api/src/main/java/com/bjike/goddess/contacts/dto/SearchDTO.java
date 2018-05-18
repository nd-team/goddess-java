package com.bjike.goddess.contacts.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.contacts.enums.ContactsStatus;

/**
 * 内部通讯录数据传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SearchDTO extends BaseDTO {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 联系电话
     */
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}