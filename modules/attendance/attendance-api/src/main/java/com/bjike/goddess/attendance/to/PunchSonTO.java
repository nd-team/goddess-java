package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.PunchSource;
import com.bjike.goddess.attendance.enums.PunchType;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 打卡子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PunchSonTO extends BaseTO {
    public interface PHONE{}
    public interface PC{}
    public interface ISPUNCH{}
    /**
     * 经度
     */
    @NotNull(groups = PunchSonTO.PHONE.class,message = "经度不能为空")
    private Double longitude;

    /**
     * 纬度
     */
    @NotNull(groups = PunchSonTO.PHONE.class,message = "纬度不能为空")
    private Double latitude;
    /**
     * 打卡地点
     */
    @NotBlank(groups = PunchSonTO.PHONE.class,message = "打卡地点不能为空")
    private String area;
    /**
     * 打卡类型
     */
    @NotNull(groups = {PunchSonTO.PHONE.class,PunchSonTO.PC.class,PunchSonTO.ISPUNCH.class},message = "打卡类型不能为空")
    private PunchType punchType;
    /**
     * 打卡来源
     */
    private PunchSource punchSource;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public PunchSource getPunchSource() {
        return punchSource;
    }

    public void setPunchSource(PunchSource punchSource) {
        this.punchSource = punchSource;
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

    public PunchType getPunchType() {
        return punchType;
    }

    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
    }
}