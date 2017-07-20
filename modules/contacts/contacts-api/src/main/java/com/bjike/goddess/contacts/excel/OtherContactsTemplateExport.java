package com.bjike.goddess.contacts.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 其他通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:44 ]
 * @Description: [ 其他通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OtherContactsTemplateExport extends BaseEntity {

    /**
     * 服务类别
     */
    @ExcelHeader(name = "服务类别", notNull = true)
    private String type;

    /**
     * 公司名称
     */
    @ExcelHeader(name = "公司名称", notNull = true)
    private String name;

    /**
     * 联系电话
     */
    @ExcelHeader(name = "联系电话", notNull = true)
    private String phone;

    /**
     * 公司地址
     */
    @ExcelHeader(name = "公司地址", notNull = false)
    private String address;

    /**
     * 使用人
     */
    @ExcelHeader(name = "使用人", notNull = false)
    private String user;

    /**
     * 使用日期
     */
    @ExcelHeader(name = "使用日期", notNull = false)
    private LocalDate useDate;

    /**
     * 评价
     */
    @ExcelHeader(name = "评价", notNull = false)
    private String evaluate;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = false)
    private String remark;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getUseDate() {
        return useDate;
    }

    public void setUseDate(LocalDate useDate) {
        this.useDate = useDate;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}