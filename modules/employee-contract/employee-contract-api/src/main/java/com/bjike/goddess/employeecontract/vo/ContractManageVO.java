package com.bjike.goddess.employeecontract.vo;

/**
 * 合同管理表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 合同管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractManageVO {

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
     * 用人单位(甲方)
     */
    private String employeeUnit;

    /**
     * 职工姓名(乙方)
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

    /**
     * 性别
     */
    private Boolean sex;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 证件类型
     */
    private String certificate;

    /**
     * 证件号码
     */
    private String certificateNumber;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 户籍地址
     */
    private String address;

    /**
     * 现住址
     */
    private String dwell;

    /**
     * 部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 是否离职
     */
    private Boolean leave;

    /**
     * 离职时间
     */
    private String leaveDate;

    /**
     * 计薪方式
     */
    private String way;

    /**
     * 基本薪酬
     */
    private Double pay;

    /**
     * 薪酬发放日
     */
    private String releaseDate;

    /**
     * 特殊情况处理
     */
    private String situation;


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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
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

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
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