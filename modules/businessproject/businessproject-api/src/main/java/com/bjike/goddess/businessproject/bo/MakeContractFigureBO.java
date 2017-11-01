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
    private Integer noMakeNum;
    /**
     * 立项
     */
    private Integer hadMakeNum;
    /**
     * 不立项
     */
    private Integer notMakeNum;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getNoMakeNum() {
        return noMakeNum;
    }

    public void setNoMakeNum(Integer noMakeNum) {
        this.noMakeNum = noMakeNum;
    }

    public Integer getHadMakeNum() {
        return hadMakeNum;
    }

    public void setHadMakeNum(Integer hadMakeNum) {
        this.hadMakeNum = hadMakeNum;
    }

    public Integer getNotMakeNum() {
        return notMakeNum;
    }

    public void setNotMakeNum(Integer notMakeNum) {
        this.notMakeNum = notMakeNum;
    }
}