package com.bjike.goddess.financeinit.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 会计科目数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountanCourseDTO extends BaseDTO {
    /**
     * 会计科目名称
     */
    private String accountanName;

    public String getAccountanName() {
        return accountanName;
    }

    public void setAccountanName(String accountanName) {
        this.accountanName = accountanName;
    }
}