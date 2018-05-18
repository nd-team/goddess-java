package com.bjike.goddess.taskallotment.bo.DayReport;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class DayBBO extends BaseBO {
    /**
     * 部门
     */
    private String depart;
    /**
     * 子表
     */
    private List<DayCBO> sons;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<DayCBO> getSons() {
        return sons;
    }

    public void setSons(List<DayCBO> sons) {
        this.sons = sons;
    }
}
