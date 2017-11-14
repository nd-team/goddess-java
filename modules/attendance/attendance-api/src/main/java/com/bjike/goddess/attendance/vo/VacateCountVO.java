package com.bjike.goddess.attendance.vo;

import java.util.List;

/**
 * 请假汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-12 17:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VacateCountVO {
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
    private List<VacateAVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<VacateAVO> getSons() {
        return sons;
    }

    public void setSons(List<VacateAVO> sons) {
        this.sons = sons;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
