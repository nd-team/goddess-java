package com.bjike.goddess.lendreimbursement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.lendreimbursement.enums.LendPhoneSelectStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请借款数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneApplyLendDTO extends BaseDTO {

    public interface TESTSELECT{}

    /**
     * 手机端列表筛选条件
     */
    @NotNull(groups = {PhoneApplyLendDTO.TESTSELECT.class},message = "筛选状态不能为空")
    private LendPhoneSelectStatus lendPhoneSelectStatus;


    public LendPhoneSelectStatus getLendPhoneSelectStatus() {
        return lendPhoneSelectStatus;
    }

    public void setLendPhoneSelectStatus(LendPhoneSelectStatus lendPhoneSelectStatus) {
        this.lendPhoneSelectStatus = lendPhoneSelectStatus;
    }
}