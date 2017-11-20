package com.bjike.goddess.contacts.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-11-16 16:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContactsCollectBO extends BaseBO {
    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;
    /**
     * 入职人数
     */
    private Integer entryNum;

    /**
     * 更新通讯录信息数
     */
    private Integer contactsInfoNum;
    /**
     * 已有通讯录数
     */
    private Integer hadContactsNum;
    /**
     * 新建项目组/部门数
     */
    private Integer projectNum;
    /**
     * 新建公邮数量
     */
    private Integer commonalityNum;
    /**
     * 已有公邮数量
     */
    private Integer hadCommonalityNum;
    /**
     * 工作邮箱检查不通过数
     */
    private Integer notPassNum;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public Integer getContactsInfoNum() {
        return contactsInfoNum;
    }

    public void setContactsInfoNum(Integer contactsInfoNum) {
        this.contactsInfoNum = contactsInfoNum;
    }

    public Integer getHadContactsNum() {
        return hadContactsNum;
    }

    public void setHadContactsNum(Integer hadContactsNum) {
        this.hadContactsNum = hadContactsNum;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }

    public Integer getCommonalityNum() {
        return commonalityNum;
    }

    public void setCommonalityNum(Integer commonalityNum) {
        this.commonalityNum = commonalityNum;
    }

    public Integer getHadCommonalityNum() {
        return hadCommonalityNum;
    }

    public void setHadCommonalityNum(Integer hadCommonalityNum) {
        this.hadCommonalityNum = hadCommonalityNum;
    }

    public Integer getNotPassNum() {
        return notPassNum;
    }

    public void setNotPassNum(Integer notPassNum) {
        this.notPassNum = notPassNum;
    }
}
