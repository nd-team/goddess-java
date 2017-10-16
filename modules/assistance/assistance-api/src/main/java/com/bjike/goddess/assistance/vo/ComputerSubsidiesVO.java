package com.bjike.goddess.assistance.vo;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.assistance.enums.Usage;
import com.bjike.goddess.organize.enums.StaffStatus;

import java.time.LocalDate;

/**
 * 电脑补助表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComputerSubsidiesVO {

    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 姓名
     */
    private String name;

    /**
     * 入职日期
     */
    private String entryDate;

    /**
     * 是否领用电脑
     */
    private Boolean necklineComputer;

    /**
     * 使用情况
     */
    private Usage usage;

    /**
     * 电脑补助额
     */
    private Double computerAmount;

    /**
     * 是否确认
     */
    private Boolean confirm;

    /**
     * 确认时间
     */
    private LocalDate confirmDate;

    /**
     * 补助状态
     */
    private SubsidiesStatus subsidiesStatus;

    /**
     * 状态
     */
    private String staffStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Boolean getNecklineComputer() {
        return necklineComputer;
    }

    public void setNecklineComputer(Boolean necklineComputer) {
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

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }

    public LocalDate getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(LocalDate confirmDate) {
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