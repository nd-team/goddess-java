package com.bjike.goddess.taskallotment.vo;

import java.util.List;

/**
 * 分配及确认汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-18 17:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ConfirmCountVO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<ConfirmSonVO> confirmSonS;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<ConfirmSonVO> getConfirmSonS() {
        return confirmSonS;
    }

    public void setConfirmSonS(List<ConfirmSonVO> confirmSonS) {
        this.confirmSonS = confirmSonS;
    }
}
