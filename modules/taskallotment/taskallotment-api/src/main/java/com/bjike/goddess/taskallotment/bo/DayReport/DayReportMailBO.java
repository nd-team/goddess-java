package com.bjike.goddess.taskallotment.bo.DayReport;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-11-07 18:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DayReportMailBO extends BaseBO {
    private String depart;
    private List<DayDBO> sons;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<DayDBO> getSons() {
        return sons;
    }

    public void setSons(List<DayDBO> sons) {
        this.sons = sons;
    }
}
