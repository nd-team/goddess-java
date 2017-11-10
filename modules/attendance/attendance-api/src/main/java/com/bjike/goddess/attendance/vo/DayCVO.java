package com.bjike.goddess.attendance.vo;

import java.util.List;

/**
 * 日报汇总第三层子表
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 10:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DayCVO {
    /**
     * id
     */
    private String id;
    /**
     * 岗位
     */
    private String position;
    /**
     * 子表
     */
    private List<DayDVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<DayDVO> getSons() {
        return sons;
    }

    public void setSons(List<DayDVO> sons) {
        this.sons = sons;
    }
}
