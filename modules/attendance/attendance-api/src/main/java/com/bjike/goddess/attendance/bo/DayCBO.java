package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 日报汇总第三层子表
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 10:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DayCBO extends BaseBO {
    /**
     * 岗位
     */
    private String position;
    /**
     * 子表
     */
    private List<DayDBO> sons;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<DayDBO> getSons() {
        return sons;
    }

    public void setSons(List<DayDBO> sons) {
        this.sons = sons;
    }
}
