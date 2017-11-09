package com.bjike.goddess.attendance.vo;

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
public class OutWorkCVO {
    /**
     * id
     */
    private String id;
    /**
     * 岗位
     */
    private String position;
    /**
     * 子表
     */
    private List<OutWorkDVO> sons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<OutWorkDVO> getSons() {
        return sons;
    }

    public void setSons(List<OutWorkDVO> sons) {
        this.sons = sons;
    }
}
