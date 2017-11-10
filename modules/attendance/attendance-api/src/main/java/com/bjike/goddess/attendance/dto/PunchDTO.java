package com.bjike.goddess.attendance.dto;

import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.attendance.enums.TotalType;
import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 打卡数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:21 ]
 * @Description: [ 打卡数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PunchDTO extends BaseDTO {
    public interface RANGE {
    }

    public interface COUNT {
    }

    /**
     * 姓名
     */
    private String name;
    /**
     * 汇总类型
     */
    @NotNull(groups = PunchDTO.COUNT.class, message = "汇总类型不能为空")
    private CountType countType;
    /**
     * 统计类型
     */
    private TotalType totalType;
    /**
     * 部门数组
     */
    private String[] departs;
    /**
     * 开始时间
     */
    @NotBlank(groups = PunchDTO.COUNT.class, message = "汇总类型不能为空")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(groups = PunchDTO.COUNT.class, message = "汇总类型不能为空")
    private String endTime;

    /**
     * 当前经度
     */
    @NotNull(groups = PunchDTO.RANGE.class, message = "当前经度不能为空")
    private Double longitude;
    /**
     * 当前纬度
     */
    @NotNull(groups = PunchDTO.RANGE.class, message = "当前纬度不能为空")
    private Double latitude;
    /**
     * 当前位置
     */
    @NotBlank(groups = PunchDTO.RANGE.class, message = "当前位置不能为空")
    private String area;

    public TotalType getTotalType() {
        return totalType;
    }

    public void setTotalType(TotalType totalType) {
        this.totalType = totalType;
    }

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }

    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}