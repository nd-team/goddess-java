package com.bjike.goddess.taskallotment.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.taskallotment.enums.CountType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 任务节点数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:10 ]
 * @Description: [ 任务节点数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TaskNodeDTO extends BaseDTO {
    public interface COUNT {
    }

    public interface PERSON {
    }

    public interface NAME{}

    /**
     * 汇总类型
     */
    private CountType countType;
    /**
     * 开始时间
     */
    @NotBlank(groups = {TaskNodeDTO.COUNT.class, TaskNodeDTO.PERSON.class}, message = "开始时间不能为空")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(groups = {TaskNodeDTO.COUNT.class, TaskNodeDTO.PERSON.class}, message = "结束时间不能为空")
    private String endTime;
    /**
     * 地区
     */
    @NotNull(groups = {TaskNodeDTO.PERSON.class}, message = "地区不能为空")
    private String[] area;
    /**
     * 项目组/部门
     */
    @NotNull(groups = {TaskNodeDTO.PERSON.class}, message = "项目组/部门不能为空")
    private String[] depart;
    /**
     * 员工
     */
    @NotNull(groups = {TaskNodeDTO.PERSON.class}, message = "员工不能为空")
    private String[] name;
    /**
     * 项目表
     */
    private String[] tables;

    /**
     * 部门id
     */
    @NotNull(groups = TaskNodeDTO.NAME.class,message = "部门id不能为空")
    private String[] deparIds;

    public String[] getDeparIds() {
        return deparIds;
    }

    public void setDeparIds(String[] deparIds) {
        this.deparIds = deparIds;
    }

    public String[] getTables() {
        return tables;
    }

    public void setTables(String[] tables) {
        this.tables = tables;
    }

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public String[] getDepart() {
        return depart;
    }

    public void setDepart(String[] depart) {
        this.depart = depart;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }
}