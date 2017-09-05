package com.bjike.goddess.contacts.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 外部通讯录数据传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExternalContactsDTO extends BaseDTO {
    /**
     * 用户名
     */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}