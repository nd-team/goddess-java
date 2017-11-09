package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-11-07 17:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VacateMailBO extends BaseBO {
    private String depart;
    private List<VacateDBO> sons;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<VacateDBO> getSons() {
        return sons;
    }

    public void setSons(List<VacateDBO> sons) {
        this.sons = sons;
    }
}
