package com.bjike.goddess.taskallotment.bo.DayReport;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 日报汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 10:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DayReportCountBO extends BaseBO {
    /**
     * 时间
     */
    private String time;
    /**
     * 子表
     */
    private List<DayABO> sons;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DayABO> getSons() {
        return sons;
    }

    public void setSons(List<DayABO> sons) {
        this.sons = sons;
    }
}
