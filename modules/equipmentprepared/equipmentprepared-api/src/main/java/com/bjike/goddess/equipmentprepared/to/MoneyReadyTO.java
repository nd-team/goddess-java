package com.bjike.goddess.equipmentprepared.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 资金准备审核
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-16 11:15 ]
 * @Description: [ 资金准备审核 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyReadyTO extends BaseTO {

    /**
     * 类别
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "类别不能为空")
    private String category;

    /**
     * 科目
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "科目不能为空")
    private String subject;

    /**
     * 总准备金
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "总准备金不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, inclusive = false, message = "总准备金必须大于0")
    private Double totalReserve;

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组不能为空")
    private String groupTeam;

    /**
     * 比例分配
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "比例分配不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, inclusive = false, message = "比例分配必须大于0")
    private Double prorate;

    /**
     * 准备金
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "准备金不能为空")
    @DecimalMin(value = "0.00", groups = {ADD.class, EDIT.class}, inclusive = false, message = "准备金必须大于0")
    private Double reserve;

    /**
     * 年份
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "年份不能为空")
    @Min(value = 0, groups = {ADD.class, EDIT.class}, message = "年份必须大于0")
    private Integer year;

    /**
     * 月份
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "月份不能为空")
    @Min(value = 1, groups = {ADD.class, EDIT.class}, message = "月份必须大于等于1")
    @Max(value = 12, groups = {ADD.class, EDIT.class}, message = "月份必须小于等于12")
    private Integer month;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalReserve() {
        return totalReserve;
    }

    public void setTotalReserve(Double totalReserve) {
        this.totalReserve = totalReserve;
    }

    public String getGroupTeam() {
        return groupTeam;
    }

    public void setGroupTeam(String groupTeam) {
        this.groupTeam = groupTeam;
    }

    public Double getProrate() {
        return prorate;
    }

    public void setProrate(Double prorate) {
        this.prorate = prorate;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}