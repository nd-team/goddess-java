package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 打卡业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:21 ]
 * @Description: [ 打卡业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PunchPhoneBO extends BaseBO {

    /**
     * 日期
     */
    private String date;

    /**
     * 项目组
     */
    private String project;

    /**
     * 姓名
     */
    private String name;
    /**
     * 上班信息
     */
    private List<PunchSonPhoneBO> gos;
    /**
     * 下班信息
     */
    private List<PunchSonPhoneBO> afters;

    public List<PunchSonPhoneBO> getGos() {
        return gos;
    }

    public void setGos(List<PunchSonPhoneBO> gos) {
        this.gos = gos;
    }

    public List<PunchSonPhoneBO> getAfters() {
        return afters;
    }

    public void setAfters(List<PunchSonPhoneBO> afters) {
        this.afters = afters;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}