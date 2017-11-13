package com.bjike.goddess.businessproject.vo;

/**
 * @Author: [xiazhili]
 * @Date: [2017-11-11 11:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectUpdateVO {
    /**
     * 内部项目名称
     */
    private String innerName;
    /**
     * 修改的总规模数量
     */
    private Double scaleContract;

    /**
     * 修改的实际完成规模数量
     */
    private Double finishScale;

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public Double getScaleContract() {
        return scaleContract;
    }

    public void setScaleContract(Double scaleContract) {
        this.scaleContract = scaleContract;
    }

    public Double getFinishScale() {
        return finishScale;
    }

    public void setFinishScale(Double finishScale) {
        this.finishScale = finishScale;
    }
}
