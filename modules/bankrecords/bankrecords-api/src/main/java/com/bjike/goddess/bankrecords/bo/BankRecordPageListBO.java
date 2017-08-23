package com.bjike.goddess.bankrecords.bo;

import com.bjike.goddess.bankrecords.beanlist.Detail;
import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 银行流水表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BankRecordPageListBO extends BaseBO {

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