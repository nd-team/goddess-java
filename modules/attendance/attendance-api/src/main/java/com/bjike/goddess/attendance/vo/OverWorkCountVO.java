package com.bjike.goddess.attendance.vo;

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
public class OverWorkCountVO {
    /**
     * id
     */
    private String id;
    /**
     * 时间
     */
    private String time;
    /**
     * 子表
     */
    private List<OutWorkAVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<OutWorkAVO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkAVO> sons) {
        this.sons = sons;
    }
}
