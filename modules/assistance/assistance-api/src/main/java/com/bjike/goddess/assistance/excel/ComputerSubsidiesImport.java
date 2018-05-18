package com.bjike.goddess.assistance.excel;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.assistance.enums.Usage;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.organize.enums.StaffStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 电脑补助导出
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助导出 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComputerSubsidiesImport extends BaseBO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门", notNull = true)
    private String department;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 入职日期
     */
    @ExcelHeader(name = "入职日期", notNull = true)
    private String entryDate;

    /**
     * 是否领用电脑
     */
    @ExcelHeader(name = "是否领用电脑")
    private String necklineComputer;

    /**
     * 使用情况
     */
    @ExcelHeader(name = "使用情况")
    private Usage usage;

    /**
     * 电脑补助额
     */
    @ExcelHeader(name = "电脑补助额")
    private Double computerAmount;

    /**
     * 是否确认
     */
    @ExcelHeader(name = "是否确认")
    private String confirm;

    /**
     * 确认时间
     */
    @ExcelHeader(name = "确认时间")
    private String confirmDate;

    /**
     * 补助状态
     */
    @ExcelHeader(name = "补助状态", notNull = true)
    private SubsidiesStatus subsidiesStatus;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态", notNull = true)
    private String staffStatus;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getNecklineComputer() {
        return necklineComputer;
    }

    public void setNecklineComputer(String necklineComputer) {
        this.necklineComputer = necklineComputer;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public Double getComputerAmount() {
        return computerAmount;
    }

    public void setComputerAmount(Double computerAmount) {
        this.computerAmount = computerAmount;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public SubsidiesStatus getSubsidiesStatus() {
        return subsidiesStatus;
    }

    public void setSubsidiesStatus(SubsidiesStatus subsidiesStatus) {
        this.subsidiesStatus = subsidiesStatus;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }
}