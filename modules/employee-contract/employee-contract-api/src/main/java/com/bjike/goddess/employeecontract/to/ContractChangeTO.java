package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 合同变更详细
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:23 ]
 * @Description: [ 合同变更详细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractChangeTO extends BaseTO {

    /**
     * 合同信息id
     */
    @NotBlank(message = "合同信息id不能为空", groups = {EDIT.class})
    private String contractId;

    /**
     * 是否签订新合同
     */
    private Boolean sign;

    /**
     * 是否添加协议
     */
    private Boolean superinduce;

    /**
     * 是否删减协议
     */
    private Boolean prune;

    /**
     * 是否续签协议
     */
    private Boolean renew;

    /**
     * 是否解除合同
     */
    private Boolean relieve;

    /**
     * 变更原因
     */
    @NotBlank(message = "变更原因不能为空", groups = {ADD.class, EDIT.class})
    private String reason;

    /**
     * 变更内容
     */
    @NotBlank(message = "变更内容不能为空", groups = {ADD.class, EDIT.class})
    private String content;

    /**
     * 是否确认已变更
     */
    private Boolean affirm;

    /**
     * 变更时间
     */
    @NotBlank(message = "不能为空", groups = {ADD.class, EDIT.class})
    private String change;


    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Boolean getSign() {
        return sign;
    }

    public void setSign(Boolean sign) {
        this.sign = sign;
    }

    public Boolean getSuperinduce() {
        return superinduce;
    }

    public void setSuperinduce(Boolean superinduce) {
        this.superinduce = superinduce;
    }

    public Boolean getPrune() {
        return prune;
    }

    public void setPrune(Boolean prune) {
        this.prune = prune;
    }

    public Boolean getRenew() {
        return renew;
    }

    public void setRenew(Boolean renew) {
        this.renew = renew;
    }

    public Boolean getRelieve() {
        return relieve;
    }

    public void setRelieve(Boolean relieve) {
        this.relieve = relieve;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getAffirm() {
        return affirm;
    }

    public void setAffirm(Boolean affirm) {
        this.affirm = affirm;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}