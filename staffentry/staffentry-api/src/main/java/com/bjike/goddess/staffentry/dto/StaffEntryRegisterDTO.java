package com.bjike.goddess.staffentry.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 10:23]
 * @Description: [用户注册数据传输]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StaffEntryRegisterDTO extends BaseDTO {

    /**
     * 账号告知的用户个人邮箱
     */
    private String emailAccount;


    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }
}
