package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class VacateBBO extends BaseBO {
    /**
     * 部门
     */
    private String depart;
    /**
     * 子表
     */
    private List<VacateCBO> sons;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<VacateCBO> getSons() {
        return sons;
    }

    public void setSons(List<VacateCBO> sons) {
        this.sons = sons;
    }
}
