package com.bjike.goddess.attendance.vo;

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
public class VacateCVO {
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
    private List<VacateDVO> sons;

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

    public List<VacateDVO> getSons() {
        return sons;
    }

    public void setSons(List<VacateDVO> sons) {
        this.sons = sons;
    }
}
