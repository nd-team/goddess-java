package com.bjike.goddess.attendance.bo.overtime;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class OutWorkBBO extends BaseBO {
    /**
     * 部门
     */
    private String depart;
    /**
     * 子表
     */
    private List<OutWorkCBO> sons;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<OutWorkCBO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkCBO> sons) {
        this.sons = sons;
    }
}
