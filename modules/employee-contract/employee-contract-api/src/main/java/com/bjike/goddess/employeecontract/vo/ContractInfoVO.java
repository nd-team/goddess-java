package com.bjike.goddess.employeecontract.vo;

/**
 * 合同信息表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 合同信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractInfoVO {

    /**
     * id
     */
    private String id;

    /**
     * 合同类型id
     */
    private String typeId;

    /**
     * 合同类型
     */
    private String typeName;

    /**
     * 合同性质id
     */
    private String natureId;

    /**
     * 合同性质
     */
    private String natureName;

    /**
     * 合同编号
     */
    private String serialNumber;

    /**
     * 用人单位(乙方)
     */
    private String employeeUnit;

    /**
     * 职工姓名(甲方)
     */
    private String username;

    /**
     * 合同期限
     */
    private String deadline;

    /**
     * 合同起始日
     */
    private String startDate;

    /**
     * 合同约满日
     */
    private String endDate;

    /**
     * 试用期时长
     */
    private String probation;

    /**
     * 线上存储位置
     */
    private String lineStorage;

    /**
     * 线下存储位置
     */
    private String storage;

    /**
     * 合同状态
     */
    private Boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNatureId() {
        return natureId;
    }

    public void setNatureId(String natureId) {
        this.natureId = natureId;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProbation() {
        return probation;
    }

    public void setProbation(String probation) {
        this.probation = probation;
    }

    public String getLineStorage() {
        return lineStorage;
    }

    public void setLineStorage(String lineStorage) {
        this.lineStorage = lineStorage;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}