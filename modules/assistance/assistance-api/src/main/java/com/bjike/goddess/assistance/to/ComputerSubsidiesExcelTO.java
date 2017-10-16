package com.bjike.goddess.assistance.to;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.assistance.enums.Usage;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.organize.enums.StaffStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * 电脑补助导入
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助导入 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComputerSubsidiesExcelTO extends BaseEntity {

    /**
     * 地区
     */
    @NotNull(message = "地区不能为空",groups = {ADD.class})
    private String area;

    /**
     * 项目组/部门
     */
    @NotNull(message = "项目组/部门不能为空",groups = {ADD.class})
    private String department;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空",groups = {ADD.class})
    private String name;

    /**
     * 入职日期
     */
    @NotNull(message = "入职日期不能为空",groups = {ADD.class})
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
    private String confirmDate;

    /**
     * 补助状态
     */
    @NotNull(message = "补助状态不能为空",groups = {ADD.class})
    private SubsidiesStatus subsidiesStatus;

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

}