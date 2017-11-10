package com.bjike.goddess.businessproject.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-11-10 14:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PersonCollectBO extends BaseBO {
    /**
     * 内部项目名称
     */
    private String innerName;
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

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
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
