package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.assistance.enums.Usage;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.organize.enums.StaffStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 电脑补助
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_computersubsidies")
public class ComputerSubsidies extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 姓名
     */
    @Column( nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 入职日期
     */
    @Column( nullable = false, columnDefinition = "DATE   COMMENT '入职日期'")
    private String entryDate;

    /**
     * 是否领用电脑
     */
    @Column(name = "is_necklineComputer", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否领用电脑'", insertable = false)
    private Boolean necklineComputer;

    /**
     * 使用情况
     */
    @Column(name = "usages",  columnDefinition = "TINYINT(2)   COMMENT '使用情况'")
    private Usage usage;

    /**
     * 电脑补助额
     */
    @Column(name = "computerAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '电脑补助额'")
    private Double computerAmount;

    /**
     * 是否确认
     */
    @Column(name = "is_confirm",  columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否确认'", insertable = false)
    private Boolean confirm;

    /**
     * 确认时间
     */
    @Column(name = "confirmDate",  columnDefinition = "DATE   COMMENT '确认时间'")
    private LocalDate confirmDate;

    /**
     * 补助状态
     */
    @Column(name = "subsidiesStatus", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '补助状态'")
    private SubsidiesStatus subsidiesStatus;

    /**
     * 状态
     */
    @Column(name = "staffStatus", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private StaffStatus staffStatus;

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

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }
}