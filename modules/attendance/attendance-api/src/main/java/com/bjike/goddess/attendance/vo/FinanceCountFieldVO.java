package com.bjike.goddess.attendance.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 财务出勤汇总字段信息
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-18 20:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FinanceCountFieldVO {
    /**
     * 字段名
     */
    private String title;
    /**
     * 字段下标
     */
    private Integer index;
    /**
     * 详细字段信息
     */
    private List<FinanceCountFieldVO> sons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<FinanceCountFieldVO> getSons() {
        return sons;
    }

    public void setSons(List<FinanceCountFieldVO> sons) {
        this.sons = sons;
    }
}
