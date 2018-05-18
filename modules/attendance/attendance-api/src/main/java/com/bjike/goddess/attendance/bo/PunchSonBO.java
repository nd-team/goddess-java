package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.PunchSource;
import com.bjike.goddess.attendance.enums.PunchType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 打卡子表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PunchSonBO extends BaseBO {

    /**
     * 打卡时间
     */
    private String punchTime;

    /**
     * 打卡地点
     */
    private String area;
    /**
     * 打卡状态
     */
    private String punchStatus;
    /**
     * 打卡来源
     */
    private PunchSource punchSource;
    /**
     * 打卡类型
     */
    private PunchType punchType;


    public PunchType getPunchType() {
        return punchType;
    }

    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
    }

    public String getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(String punchStatus) {
        this.punchStatus = punchStatus;
    }

    public PunchSource getPunchSource() {
        return punchSource;
    }

    public void setPunchSource(PunchSource punchSource) {
        this.punchSource = punchSource;
    }

    public String getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(String punchTime) {
        this.punchTime = punchTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}