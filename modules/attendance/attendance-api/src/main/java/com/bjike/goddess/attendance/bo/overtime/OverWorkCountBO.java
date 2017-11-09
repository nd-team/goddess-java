package com.bjike.goddess.attendance.bo.overtime;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 加班汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-23 13:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OverWorkCountBO extends BaseBO {
    /**
     * 时间
     */
    private String time;
    /**
     * 子表
     */
    private List<OutWorkABO> sons;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<OutWorkABO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkABO> sons) {
        this.sons = sons;
    }
}
