package com.bjike.goddess.lendreimbursement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 借款审核人员数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:06 ]
 * @Description: [ 借款审核人员数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LendAuditDetailDTO extends BaseDTO {

    /**
     * 借款id
     */
    private String applyLendId;

    public String getApplyLendId() {
        return applyLendId;
    }

    public void setApplyLendId(String applyLendId) {
        this.applyLendId = applyLendId;
    }
}