package com.bjike.goddess.employeecontract.vo;

/**
 * 合同变更详细表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:23 ]
 * @Description: [ 合同变更详细表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractChangeVO {

    /**
     * id
     */
    private String id;

    /**
     * 合同信息
     */
    private String contract_id;

    /**
     * 合同编号
     */
    private String serialNumber;

    /**
     * 合同类型
     */
    private String type;

    /**
     * 合同性质
     */
    private String nature;

    /**
     * 用人单位(乙方)
     */
    private String employeeUnit;

    /**
     * 职工姓名(甲方)
     */
    private String username;

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
    private String reason;

    /**
     * 变更内容
     */
    private String content;

    /**
     * 是否确认已变更
     */
    private Boolean affirm;

    /**
     * 变更时间
     */
    private String change;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getEmployeeUnit() {
        return employeeUnit;
    }

    public void setEmployeeUnit(String employeeUnit) {
        this.employeeUnit = employeeUnit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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