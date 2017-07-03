package com.bjike.goddess.checkfunds.vo;

import com.bjike.goddess.checkfunds.beanlist.Detail;

import java.util.List;

/**
 * 银行流水明细
 * @Author: [chenjunhao]
 * @Date: [2017-06-13 18:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BankDetailVO{
    /**
     * id
     */
    private String id;

    /**
     * 银行流水行记录字段
     */
    List<Detail> detailList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }
}
