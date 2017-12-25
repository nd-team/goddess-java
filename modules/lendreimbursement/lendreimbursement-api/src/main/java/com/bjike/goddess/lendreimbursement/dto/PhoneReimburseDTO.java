package com.bjike.goddess.lendreimbursement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.lendreimbursement.enums.LendPhoneSelectStatus;
import com.bjike.goddess.lendreimbursement.enums.ReimPhoneSelectStatus;

import javax.validation.constraints.NotNull;

/**
 * 手机报销数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 手机报销数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneReimburseDTO extends BaseDTO {

    public interface TESTSELECT{}

    /**
     * 手机端列表筛选条件
     */
    @NotNull(groups = {PhoneReimburseDTO.TESTSELECT.class},message = "筛选状态不能为空")
    private ReimPhoneSelectStatus reimPhoneSelectStatus;


    public ReimPhoneSelectStatus getReimPhoneSelectStatus() {
        return reimPhoneSelectStatus;
    }

    public void setReimPhoneSelectStatus(ReimPhoneSelectStatus reimPhoneSelectStatus) {
        this.reimPhoneSelectStatus = reimPhoneSelectStatus;
    }
}