package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.customer.enums.CustomerSex;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import org.hibernate.validator.constraints.Email;

/**
 * 商务通讯录
 *
 * @Author: [zhuangkaiqin]
 * @Date: [2017-07-10 18:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CommerceContactsTemplateExport {

    /**
     * 客户信息编号
     */
    @ExcelHeader(name = "客户信息编号" , notNull = true )
    private String customerNum;

    /**
     * 客户姓名
     */
    @ExcelHeader(name = "客户姓名" , notNull = true )
    private String customerName;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区" , notNull = true )
    private String area;

    /**
     * 性别
     */
    @ExcelHeader(name = "性别" , notNull = true )
    private CustomerSex customerSex;

    /**
     * 客户类别
     */
    @ExcelHeader(name = "客户类别" , notNull = true )
    private CustomerType customerType;

    /**
     * 客户状态
     */
    @ExcelHeader(name = "客户状态" , notNull = true )
    private CustomerStatus customerStatus;

    /**
     * 关系程度
     */
    @ExcelHeader(name = "关系程度" , notNull = true )
    private Double relation;

    /**
     * 客户级别
     */
    @ExcelHeader(name = "客户级别" , notNull = true )
    private String customerLevelName;

    /**
     * 客户来源
     */
    @ExcelHeader(name = "客户来源" , notNull = true )
    private String origin;

    /**
     * 介绍人
     */
    @ExcelHeader(name = "介绍人" , notNull = true )
    private String introducer;

    /**
     * 邮箱
     */
    @Email
    @ExcelHeader(name = "邮箱" , notNull = true )
    private String cusEmail;

    /**
     * 手机号
     */
    @ExcelHeader(name = "手机号" , notNull = true )
    private String tel;

    /**
     * 座机
     */
    @ExcelHeader(name = "座机" , notNull = true )
    private String phone;

    /**
     * 微信
     */
    @ExcelHeader(name = "微信" , notNull = true )
    private String weChart;

    /**
     * QQ号
     */
    @ExcelHeader(name = "QQ号" , notNull = true )
    private String qq;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位" , notNull = true )
    private String workPosition;

    /**
     * 职级
     */
    @ExcelHeader(name = "职级" , notNull = true )
    private String workLevel;

    /**
     * 职权
     */
    @ExcelHeader(name = "职权" , notNull = true )
    private String workRight;

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public CustomerSex getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(CustomerSex customerSex) {
        this.customerSex = customerSex;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Double getRelation() {
        return relation;
    }

    public void setRelation(Double relation) {
        this.relation = relation;
    }

    public String getCustomerLevelName() {
        return customerLevelName;
    }

    public void setCustomerLevelName(String customerLevelName) {
        this.customerLevelName = customerLevelName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeChart() {
        return weChart;
    }

    public void setWeChart(String weChart) {
        this.weChart = weChart;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getWorkLevel() {
        return workLevel;
    }

    public void setWorkLevel(String workLevel) {
        this.workLevel = workLevel;
    }

    public String getWorkRight() {
        return workRight;
    }

    public void setWorkRight(String workRight) {
        this.workRight = workRight;
    }
}
