package com.bjike.goddess.taskallotment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class ConfirmCountBO extends BaseBO {
    /**
     * 地区
     */
    private String area;
    /**
     * 子表
     */
    private List<ConfirmSonBO> confirmSonS;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<ConfirmSonBO> getConfirmSonS() {
        return confirmSonS;
    }

    public void setConfirmSonS(List<ConfirmSonBO> confirmSonS) {
        this.confirmSonS = confirmSonS;
    }
}
