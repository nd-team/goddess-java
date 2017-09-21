package com.bjike.goddess.taskallotment.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.taskallotment.enums.CountType;

import java.util.Set;

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
    /**
     * 汇总类型
     */
    private CountType countType;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 地区
     */
    private Set<String> area;
    /**
     * 项目组/部门
     */
    private Set<String> depart;
    /**
     * 员工
     */
    private Set<String> name;

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

    public Set<String> getArea() {
        return area;
    }

    public void setArea(Set<String> area) {
        this.area = area;
    }

    public Set<String> getDepart() {
        return depart;
    }

    public void setDepart(Set<String> depart) {
        this.depart = depart;
    }

    public Set<String> getName() {
        return name;
    }

    public void setName(Set<String> name) {
        this.name = name;
    }
}