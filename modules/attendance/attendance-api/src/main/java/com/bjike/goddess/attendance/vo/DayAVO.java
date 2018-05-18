package com.bjike.goddess.attendance.vo;

import java.util.List;

/**
 * 日报汇总第一层子表
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 10:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DayAVO {
    /**
     * id
     */
    private String id;
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<DayBVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DayBVO> getSons() {
        return sons;
    }

    public void setSons(List<DayBVO> sons) {
        this.sons = sons;
    }
}
