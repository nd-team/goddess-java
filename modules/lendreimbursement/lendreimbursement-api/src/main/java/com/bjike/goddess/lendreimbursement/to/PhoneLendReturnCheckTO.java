package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.lendreimbursement.enums.LendRetunStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请借款还款核对
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款还款核对 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneLendReturnCheckTO extends BaseTO {

    public interface TESTRETURNCHECK{}


    /**
     * 是否收到单据(是或否)
     */
    @NotBlank(groups = {PhoneLendReturnCheckTO.TESTRETURNCHECK.class} , message = "是否收到单据(是或否)不能为空")
    private String documentCondition;

    /**
     * 核对内容
     */
    @NotBlank(groups = {PhoneLendReturnCheckTO.TESTRETURNCHECK.class} , message = "核对内容不能为空")
    private String checkcontent;

    /**
     * 还款核对状态
     */
    @NotNull(groups = {PhoneLendReturnCheckTO.TESTRETURNCHECK.class} , message = "核对内容不能为空")
    private LendRetunStatus lendRetunStatus;

    public String getCheckcontent() {
        return checkcontent;
    }

    public void setCheckcontent(String checkcontent) {
        this.checkcontent = checkcontent;
    }

    public LendRetunStatus getLendRetunStatus() {
        return lendRetunStatus;
    }

    public void setLendRetunStatus(LendRetunStatus lendRetunStatus) {
        this.lendRetunStatus = lendRetunStatus;
    }

    public String getDocumentCondition() {
        return documentCondition;
    }

    public void setDocumentCondition(String documentCondition) {
        this.documentCondition = documentCondition;
    }
}