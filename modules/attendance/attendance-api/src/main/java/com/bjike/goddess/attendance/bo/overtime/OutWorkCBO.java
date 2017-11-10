package com.bjike.goddess.attendance.bo.overtime;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 加班汇总第三层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-23 14:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OutWorkCBO extends BaseBO {
    /**
     * 岗位
     */
    private String position;
    /**
     * 子表
     */
    private List<OutWorkDBO> sons;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<OutWorkDBO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkDBO> sons) {
        this.sons = sons;
    }
}
