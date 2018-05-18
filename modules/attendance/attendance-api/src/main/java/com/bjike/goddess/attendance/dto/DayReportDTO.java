package com.bjike.goddess.attendance.dto;

import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 日报数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 日报数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DayReportDTO extends BaseDTO {
    public interface COUNT{}
    /**
     * 时间
     */
    private String time;
    /**
     * 姓名数组
     */
    private String[] names;
    /**
     * 汇总类型
     */
    @NotNull(groups = DayReportDTO.COUNT.class,message = "汇总类型不能为空")
    private CountType countType;
    /**
     * 部门id数组
     */
    private String[] departIds;
    /**
     * 开始时间
     */
    @NotBlank(groups = DayReportDTO.COUNT.class,message = "开始时间不能为空")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(groups = DayReportDTO.COUNT.class,message = "结束时间不能为空")
    private String endTime;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }

    public String[] getDepartIds() {
        return departIds;
    }

    public void setDepartIds(String[] departIds) {
        this.departIds = departIds;
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

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}