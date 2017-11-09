package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 请假汇总第一层
 * @Author: [chenjunhao]
 * @Date: [2017-10-12 18:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VacateABO extends BaseBO{
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<VacateBBO> sons;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<VacateBBO> getSons() {
        return sons;
    }

    public void setSons(List<VacateBBO> sons) {
        this.sons = sons;
    }
}
