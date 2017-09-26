package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.lendreimbursement.enums.LendIndentityStatus;
import com.bjike.goddess.lendreimbursement.enums.LendStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请借款负责人审核
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneApplyLendChargerTO extends BaseTO {

    public interface TESTChargeAudit{}

    /**
     * 审核身份（三种身份）
     */
    @NotNull(groups = {PhoneApplyLendChargerTO.TESTChargeAudit.class},message ="审核身份不能为空" )
    private LendIndentityStatus lendIndentityStatus;

    /**
     * 审核意见
     */
    private String chargerOpinion;

    /**
     * 是否通过(是或否)
     */
    @NotBlank(groups = {PhoneApplyLendChargerTO.TESTChargeAudit.class},message ="是否通过不能为空" )
    private String chargerPass;


    public LendIndentityStatus getLendIndentityStatus() {
        return lendIndentityStatus;
    }

    public void setLendIndentityStatus(LendIndentityStatus lendIndentityStatus) {
        this.lendIndentityStatus = lendIndentityStatus;
    }

    public String getChargerOpinion() {
        return chargerOpinion;
    }

    public void setChargerOpinion(String chargerOpinion) {
        this.chargerOpinion = chargerOpinion;
    }

    public String getChargerPass() {
        return chargerPass;
    }

    public void setChargerPass(String chargerPass) {
        this.chargerPass = chargerPass;
    }
}