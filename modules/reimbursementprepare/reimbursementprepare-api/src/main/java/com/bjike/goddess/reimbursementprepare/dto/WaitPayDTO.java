package com.bjike.goddess.reimbursementprepare.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 等待付款数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:01 ]
 * @Description: [ 等待付款数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitPayDTO extends BaseDTO {
    public interface PROJECTGROUP {
    }

    public interface AREA {
    }

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 项目组数组
     */
    @NotNull(groups = {WaitPayDTO.PROJECTGROUP.class}, message = "项目组不能为空")
    private String[] projectGroups;

    /**
     * 地区数组
     */
    @NotNull(groups = {WaitPayDTO.AREA.class}, message = "地区不能为空")
    private String[] areas;

    public String[] getProjectGroups() {
        return projectGroups;
    }

    public void setProjectGroups(String[] projectGroups) {
        this.projectGroups = projectGroups;
    }

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
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
}