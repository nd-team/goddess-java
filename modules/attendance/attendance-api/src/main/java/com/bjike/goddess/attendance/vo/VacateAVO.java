package com.bjike.goddess.attendance.vo;


import java.util.List;

/**
 * 请假汇总第一层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-12 18:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VacateAVO {
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
    private List<VacateBVO> sons;

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

    public List<VacateBVO> getSons() {
        return sons;
    }

    public void setSons(List<VacateBVO> sons) {
        this.sons = sons;
    }
}
