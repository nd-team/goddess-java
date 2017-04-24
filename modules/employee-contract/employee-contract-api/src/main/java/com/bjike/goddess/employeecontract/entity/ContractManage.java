package com.bjike.goddess.employeecontract.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 合同管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 合同管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "employee-contract_contractmanage")
public class ContractManage extends BaseEntity {

    /**
     * 合同类型
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "type_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '合同类型'")
    private ContractType type;

    /**
     * 合同性质
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "nature_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '合同性质'")
    private ContractNature nature;

    /**
     * 合同编号
     */
    @Column(name = "serialNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合同编号'", unique = true)
    private String serialNumber;

    /**
     * 用人单位(乙方)
     */
    @Column(name = "employeeUnit", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '用人单位(乙方)'")
    private String employeeUnit;

    /**
     * 职工姓名(甲方)
     */
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职工姓名(甲方)'")
    private String username;

    /**
     * 合同期限
     */
    @Column(name = "deadline", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '合同期限'")
    private String deadline;

    /**
     * 合同起始日
     */
    @Column(name = "startDate", nullable = false, columnDefinition = "DATE   COMMENT '合同起始日'")
    private LocalDate startDate;

    /**
     * 合同约满日
     */
    @Column(name = "endDate", nullable = false, columnDefinition = "DATE   COMMENT '合同约满日'")
    private LocalDate endDate;

    /**
     * 试用期时长
     */
    @Column(name = "probation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '试用期时长'")
    private String probation;

    /**
     * 线上存储位置
     */
    @Column(name = "lineStorage", columnDefinition = "VARCHAR(255)   COMMENT '线上存储位置'")
    private String lineStorage;

    /**
     * 线下存储位置
     */
    @Column(name = "storage", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '线下存储位置'")
    private String storage;

    /**
     * 合同状态
     */
    @Column(name = "is_status", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '合同状态'", insertable = false)
    private Boolean status;

    /**
     * 性别
     */
    @Column(name = "is_sex", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '性别'", insertable = false)
    private Boolean sex;

    /**
     * 出生日期
     */
    @Column(name = "birth", columnDefinition = "DATE   COMMENT '出生日期'")
    private LocalDate birth;

    /**
     * 证件类型
     */
    @Column(name = "certificate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证件类型'")
    private String certificate;

    /**
     * 证件号码
     */
    @Column(name = "certificateNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证件号码'")
    private String certificateNumber;

    /**
     * 联系电话
     */
    @Column(name = "phone", columnDefinition = "VARCHAR(255)   COMMENT '联系电话'")
    private String phone;

    /**
     * 户籍地址
     */
    @Column(name = "address", columnDefinition = "VARCHAR(255)   COMMENT '户籍地址'")
    private String address;

    /**
     * 现住址
     */
    @Column(name = "dwell", columnDefinition = "VARCHAR(255)   COMMENT '现住址'")
    private String dwell;

    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 是否离职
     */
    @Column(name = "is_leave", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否离职'", insertable = false)
    private Boolean leave;

    /**
     * 离职时间
     */
    @Column(name = "leaveDate", columnDefinition = "DATE   COMMENT '离职时间'")
    private LocalDate leaveDate;

    /**
     * 计薪方式
     */
    @Column(name = "way", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '计薪方式'")
    private String way;

    /**
     * 基本薪酬
     */
    @Column(name = "pay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '基本薪酬'")
    private Double pay;

    /**
     * 薪酬发放日
     */
    @Column(name = "releaseDate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '薪酬发放日'")
    private String releaseDate;

    /**
     * 特殊情况处理
     */
    @Column(name = "situation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '特殊情况处理'")
    private String situation;


    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public ContractNature getNature() {
        return nature;
    }

    public void setNature(ContractNature nature) {
        this.nature = nature;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDwell() {
        return dwell;
    }

    public void setDwell(String dwell) {
        this.dwell = dwell;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getLeave() {
        return leave;
    }

    public void setLeave(Boolean leave) {
        this.leave = leave;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}