package com.bjike.goddess.voucher.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 记账凭证部分数据
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 记账凭证部分数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnalysisBO extends BaseBO {

    /**
     * 地区/项目间/时间段
     */
    private String condition;

    /**
     * 偏差额/百分比发生额/增长率
     */
    private String ratio;

    /**
     * 是否预警
     */
    private Boolean warning;

//    /**
//     * 预警额
//     */
//    private String brow;

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}