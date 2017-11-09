package com.bjike.goddess.attendance.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 考勤情况汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-14 16:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CaseCountVO{
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<CaseAVO> sons;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<CaseAVO> getSons() {
        return sons;
    }

    public void setSons(List<CaseAVO> sons) {
        this.sons = sons;
    }
}
