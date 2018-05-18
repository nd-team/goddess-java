package com.bjike.goddess.businessproject.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 合同立项情况金额汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-23 11:36 ]
 * @Description: [ 合同立项情况金额汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MakeContractFigureBO extends BaseBO {


    /**
     * 地区
     */
    private String area;
    /**
     * 预立项总数
     */
    private Double noMakeNum;
    /**
     * 立项
     */
    private Double hadMakeNum;
    /**
     * 不立项
     */
    private Double notMakeNum;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getNoMakeNum() {
        return noMakeNum;
    }

    public void setNoMakeNum(Double noMakeNum) {
        this.noMakeNum = noMakeNum;
    }

    public Double getHadMakeNum() {
        return hadMakeNum;
    }

    public void setHadMakeNum(Double hadMakeNum) {
        this.hadMakeNum = hadMakeNum;
    }

    public Double getNotMakeNum() {
        return notMakeNum;
    }

    public void setNotMakeNum(Double notMakeNum) {
        this.notMakeNum = notMakeNum;
    }
}