package com.bjike.goddess.employeecontract.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 人员合同信息传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 人员合同信息传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ContractPersonalBO extends BaseBO {

    /**
     * 职工姓名(甲方)
     */
    private String username;

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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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