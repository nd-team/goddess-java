package com.bjike.goddess.financeinit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.CategoryName;

/**
 * 会计科目业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountAddDateBO extends BaseBO {

    /**
     * 代码
     */
    private String code;

    /**
     * 会计科目名称
     */
    private String accountanName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountanName() {
        return accountanName;
    }

    public void setAccountanName(String accountanName) {
        this.accountanName = accountanName;
    }
}