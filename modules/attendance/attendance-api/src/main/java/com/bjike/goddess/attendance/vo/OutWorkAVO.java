package com.bjike.goddess.attendance.vo;

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
public class OutWorkAVO {
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
    private List<OutWorkBVO> sons;

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

    public List<OutWorkBVO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkBVO> sons) {
        this.sons = sons;
    }
}
