package com.bjike.goddess.reportmanagement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资产和负债结构表
 * @Author: [chenjunhao]
 * @Date: [2017-06-26 14:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StructureBO extends BaseBO{
    /**
     * 项目
     */
    private String project;
    /**
     * 金额
     */
    private Double fee;
    /**
     * 比例
     */
    private String scale;
    /**
     * 最佳比例
     */
    private String bestScale;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getBestScale() {
        return bestScale;
    }

    public void setBestScale(String bestScale) {
        this.bestScale = bestScale;
    }
}
