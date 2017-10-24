package com.bjike.goddess.financeinit.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 账户来源
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:25 ]
 * @Description: [ 账户来源 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountExport extends BaseBO {

    /**
     * 用户名称(账户来源)
     */
    @ExcelHeader(name="用户名称",notNull = true)
    private String name;

    /**
     * 帐号
     */
    @ExcelHeader(name="帐号",notNull = true)
    private String account;

    /**
     * 开户行地址
     */
    @ExcelHeader(name="开户行地址",notNull = true)
    private String bankAddr;

    /**
     * 备注
     */
    @ExcelHeader(name="备注",notNull = true)
    private String remark;

    /**
     * 一级分类
     */
    @ExcelHeader(name="一级分类",notNull = true)
    private String firstSubject;

    /**
     * 二级科目
     */
    @ExcelHeader(name="二级科目",notNull = true)
    private String secondSubject;

    /**
     * 三级科目
     */
    @ExcelHeader(name="三级科目",notNull = true)
    private String thirdSubject;




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

    public String getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(String firstSubject) {
        this.firstSubject = firstSubject;
    }


}