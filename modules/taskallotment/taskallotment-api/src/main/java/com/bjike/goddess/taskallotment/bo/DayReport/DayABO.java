package com.bjike.goddess.taskallotment.bo.DayReport;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class DayABO extends BaseBO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<DayBBO> sons;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<DayBBO> getSons() {
        return sons;
    }

    public void setSons(List<DayBBO> sons) {
        this.sons = sons;
    }
}
