package com.bjike.goddess.bankrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 银行流水数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordDTO extends BaseDTO {

    public interface PageList {
    }

    /**
     * 账户Id
     */
    @NotBlank(message = "账户查询名称不能为空", groups = {BankRecordDTO.PageList.class})
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}