package com.bjike.goddess.attendance.vo;

import java.util.ArrayList;
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
public class PunchPhoneVO {

    /**
     * id
     */
    private String id;
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
    private List<PunchSonPhoneVO> gos;
    /**
     * 下班信息
     */
    private List<PunchSonPhoneVO> afters;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PunchSonPhoneVO> getGos() {
        return gos;
    }

    public void setGos(List<PunchSonPhoneVO> gos) {
        this.gos = gos;
    }

    public List<PunchSonPhoneVO> getAfters() {
        return afters;
    }

    public void setAfters(List<PunchSonPhoneVO> afters) {
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