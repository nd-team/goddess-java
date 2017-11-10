package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 请假汇总第三层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-12 17:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VacateCBO extends BaseBO {
    /**
     * 岗位
     */
    private String position;
    /**
     * 子表
     */
    private List<VacateDBO> sons;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<VacateDBO> getSons() {
        return sons;
    }

    public void setSons(List<VacateDBO> sons) {
        this.sons = sons;
    }
}
