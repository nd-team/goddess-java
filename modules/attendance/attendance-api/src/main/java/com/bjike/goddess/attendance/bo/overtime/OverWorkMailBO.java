package com.bjike.goddess.attendance.bo.overtime;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-11-07 17:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OverWorkMailBO extends BaseBO {
    private String depart;
    private List<OutWorkDBO> sons;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<OutWorkDBO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkDBO> sons) {
        this.sons = sons;
    }
}
