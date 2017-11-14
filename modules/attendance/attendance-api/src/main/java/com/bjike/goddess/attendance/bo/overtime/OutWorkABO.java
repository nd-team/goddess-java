package com.bjike.goddess.attendance.bo.overtime;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 加班汇总第一层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-23 13:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OutWorkABO extends BaseBO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<OutWorkBBO> sons;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<OutWorkBBO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkBBO> sons) {
        this.sons = sons;
    }
}
