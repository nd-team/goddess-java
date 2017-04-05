package com.bjike.goddess.financeinit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 账户来源业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:25 ]
 * @Description: [ 账户来源业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountBO extends BaseBO {

    /**
     * 用户名称
     */
    private String name;

    /**
     * 帐号
     */
    private String account;

    /**
     * 类别
     */
    private CategoryBO categoryBO;

    /**
     * 开户行地址
     */
    private String bankAddr;

    /**
     * 备注
     */
    private String remark;

    /**
     * 一级类别名
     */
    private String firstSubject;

    /**
     * 二级类别名
     */
    private String secondSubject;

    /**
     * 三级类别名
     */
    private String thirdSubject;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public CategoryBO getCategoryBO() {
        return categoryBO;
    }

    public void setCategoryBO(CategoryBO categoryBO) {
        this.categoryBO = categoryBO;
    }

    public String getBankAddr() {
        return bankAddr;
    }

    public void setBankAddr(String bankAddr) {
        this.bankAddr = bankAddr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }

    public String getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(String secondSubject) {
        this.secondSubject = secondSubject;
    }

    public String getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(String thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}