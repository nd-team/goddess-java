package com.bjike.goddess.event.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.event.enums.IntervalType;
import com.bjike.goddess.event.enums.Permissions;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 提醒间隔时间设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 04:11 ]
 * @Description: [ 提醒间隔时间设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TimeSetTO extends BaseTO {
    public interface EDIT{}
    /**
     * 权限
     */
    @NotNull(groups = TimeSetTO.EDIT.class,message = "权限不能为空")
    private Permissions permissions;
    /**
     * 间隔类型
     */
    @NotNull(groups = TimeSetTO.EDIT.class,message = "间隔类型不能为空")
    private IntervalType intervalType;
    /**
     * 间隔时长
     */
    @NotNull(groups = TimeSetTO.EDIT.class,message = "间隔时长不能为空")
    @Min(value = 1,groups = TimeSetTO.EDIT.class,message = "间隔时长必须大于0")
    private Integer interval;

    /**
     * 设置时间
     */
    @NotBlank(groups = TimeSetTO.EDIT.class,message = "设置时间不能为空")
    private String setTime;
    /**
     * 颜色
     */
    @NotBlank(groups = TimeSetTO.EDIT.class,message = "颜色不能为空")
    private String color;

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public IntervalType getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(IntervalType intervalType) {
        this.intervalType = intervalType;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}