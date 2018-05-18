package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 请假汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-12 17:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VacateCountBO extends BaseBO {
    /**
     * 时间
     */
    private String time;

    /**
     * 子表
     */
    private List<VacateABO> sons;

    public List<VacateABO> getSons() {
        return sons;
    }

    public void setSons(List<VacateABO> sons) {
        this.sons = sons;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
