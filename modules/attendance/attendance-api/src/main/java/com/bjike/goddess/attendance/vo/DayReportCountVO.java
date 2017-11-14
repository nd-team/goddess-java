package com.bjike.goddess.attendance.vo;

import java.util.List;

/**
 * 日报汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 10:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DayReportCountVO {
    /**
     * id
     */
    private String id;
    /**
     * 时间
     */
    private String time;
    /**
     * 子表
     */
    private List<DayAVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DayAVO> getSons() {
        return sons;
    }

    public void setSons(List<DayAVO> sons) {
        this.sons = sons;
    }
}
