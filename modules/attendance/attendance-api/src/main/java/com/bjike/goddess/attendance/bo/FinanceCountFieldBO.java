package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class FinanceCountFieldBO extends BaseBO {
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
    private List<FinanceCountFieldBO> sons = new ArrayList<>();

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

    public List<FinanceCountFieldBO> getSons() {
        return sons;
    }

    public void setSons(List<FinanceCountFieldBO> sons) {
        this.sons = sons;
    }
}
