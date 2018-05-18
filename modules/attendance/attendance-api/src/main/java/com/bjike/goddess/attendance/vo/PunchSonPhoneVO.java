package com.bjike.goddess.attendance.vo;

import com.bjike.goddess.attendance.enums.PunchSource;
import com.bjike.goddess.attendance.enums.PunchType;

import java.util.ArrayList;
import java.util.List;

/**
 * 打卡子表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PunchSonPhoneVO {
    /**
     * id
     */
    private String id;
    /**
     * 打卡时间
     */
    private String punchTime;

    /**
     * 打卡地点
     */
    private String area;
    /**
     * 打卡来源
     */
    private PunchSource punchSource;
    /**
     * 打卡类型
     */
    private PunchType punchType;
    /**
     * 子表
     */
    private List<PunchGrandSonVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PunchGrandSonVO> getSons() {
        return sons;
    }

    public void setSons(List<PunchGrandSonVO> sons) {
        this.sons = sons;
    }

    public PunchType getPunchType() {
        return punchType;
    }

    public void setPunchType(PunchType punchType) {
        this.punchType = punchType;
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