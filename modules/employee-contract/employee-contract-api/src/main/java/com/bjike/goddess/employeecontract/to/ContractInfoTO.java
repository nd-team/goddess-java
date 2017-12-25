package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 合同信息传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 合同信息传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractInfoTO extends BaseTO {

    /**
     * 合同类型
     */
    @NotBlank(message = "合同类型不能为空", groups = {ADD.class, EDIT.class})
    private String typeId;

    /**
     * 合同性质
     */
    @NotBlank(message = "合同性质不能为空", groups = {ADD.class, EDIT.class})
    private String natureId;

    /**
     * 合同编号
     */
    @NotBlank(message = "合同编号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 用人单位(甲方)
     */
    @NotBlank(message = "用人单位(甲方)不能为空", groups = {ADD.class, EDIT.class})
    private String employeeUnit;

    /**
     * 职工姓名(乙方)
     */
    @NotBlank(message = "职工姓名(乙方)不能为空", groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 合同期限
     */
    @NotBlank(message = "合同期限不能为空", groups = {ADD.class, EDIT.class})
    private String deadline;

    /**
     * 合同起始日
     */
    @NotBlank(message = "合同起始日不能为空", groups = {ADD.class, EDIT.class})
    private String startDate;

    /**
     * 合同约满日
     */
    @NotBlank(message = "合同约满日不能为空", groups = {ADD.class, EDIT.class})
    private String endDate;

    /**
     * 试用期时长
     */
    @NotBlank(message = "试用期时长不能为空", groups = {ADD.class, EDIT.class})
    private String probation;

    /**
     * 线上存储位置
     */
    private String lineStorage;

    /**
     * 线下存储位置
     */
    @NotBlank(message = "线下存储位置不能为空", groups = {ADD.class, EDIT.class})
    private String storage;

    /**
     * 合同状态
     */
    private Boolean status;


    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getNatureId() {
        return natureId;
    }

    public void setNatureId(String natureId) {
        this.natureId = natureId;
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