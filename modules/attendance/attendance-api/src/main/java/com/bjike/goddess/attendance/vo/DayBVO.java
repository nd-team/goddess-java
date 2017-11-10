package com.bjike.goddess.attendance.vo;

import java.util.List;

/**
 * 日报汇总第二层子表
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 10:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DayBVO {
    /**
     * id
     */
    private String id;
    /**
     * 部门
     */
    private String depart;
    /**
     * 子表
     */
    private List<DayCVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<DayCVO> getSons() {
        return sons;
    }

    public void setSons(List<DayCVO> sons) {
        this.sons = sons;
    }
}
