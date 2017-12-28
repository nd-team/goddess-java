package com.bjike.goddess.archive.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 员工档案业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CurrentMessageBO extends BaseBO {

    /**
     * 头像地址
     */
    private String headAdrees;

    /**
     * 姓名
     */
    private String username;

    /**
     * 在职状态
     */
    private Boolean incumbency;

    /**
     * 员工编号
     */
    private String serialNumber;

    /**
     * 入职时间
     */
    private String entryTime;

    /**
     * 离职时间
     */
    private String dimissionTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private String birth;

    public String getHeadAdrees() {
        return headAdrees;
    }

    public void setHeadAdrees(String headAdrees) {
        this.headAdrees = headAdrees;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIncumbency() {
        return incumbency;
    }

    public void setIncumbency(Boolean incumbency) {
        this.incumbency = incumbency;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(String dimissionTime) {
        this.dimissionTime = dimissionTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}