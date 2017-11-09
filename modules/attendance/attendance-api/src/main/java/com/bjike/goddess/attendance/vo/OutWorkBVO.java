package com.bjike.goddess.attendance.vo;

import java.util.List;

/**
 * 加班汇总第二层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-23 14:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OutWorkBVO {
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
    private List<OutWorkCVO> sons;

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

    public List<OutWorkCVO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkCVO> sons) {
        this.sons = sons;
    }
}
