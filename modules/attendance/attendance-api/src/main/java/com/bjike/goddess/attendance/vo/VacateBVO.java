package com.bjike.goddess.attendance.vo;

import java.util.List;

/**
 * 请假汇总第二层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-12 17:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VacateBVO {
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
    private List<VacateCVO> sons;

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

    public List<VacateCVO> getSons() {
        return sons;
    }

    public void setSons(List<VacateCVO> sons) {
        this.sons = sons;
    }
}
