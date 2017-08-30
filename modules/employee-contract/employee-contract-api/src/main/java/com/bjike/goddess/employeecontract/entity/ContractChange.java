package com.bjike.goddess.employeecontract.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 合同变更详细
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:23 ]
 * @Description: [ 合同变更详细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "employeecontract_change")
public class ContractChange extends BaseEntity {

    /**
     * 合同信息
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "contract_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '合同信息'")
    private ContractManage contract;

    /**
     * 是否签订新合同
     */
    @Column(name = "is_sign", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否签订新合同'", insertable = false)
    private Boolean sign;

    /**
     * 是否添加协议
     */
    @Column(name = "is_superinduce", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否添加协议'", insertable = false)
    private Boolean superinduce;

    /**
     * 是否删减协议
     */
    @Column(name = "is_prune", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否删减协议'", insertable = false)
    private Boolean prune;

    /**
     * 是否续签协议
     */
    @Column(name = "is_renew", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否续签协议'", insertable = false)
    private Boolean renew;

    /**
     * 是否解除合同
     */
    @Column(name = "is_relieve", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否解除合同'", insertable = false)
    private Boolean relieve;

    /**
     * 变更原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '变更原因'")
    private String reason;

    /**
     * 变更内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '变更内容'")
    private String content;

    /**
     * 是否确认已变更
     */
    @Column(name = "is_affirm", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否确认已变更'", insertable = false)
    private Boolean affirm;

    /**
     * 变更时间
     */
    @Column(name = "changeTime", nullable = false, columnDefinition = "DATE   COMMENT '变更时间'")
    private LocalDate change;


    public ContractManage getContract() {
        return contract;
    }

    public void setContract(ContractManage contract) {
        this.contract = contract;
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

    public LocalDate getChange() {
        return change;
    }

    public void setChange(LocalDate change) {
        this.change = change;
    }
}