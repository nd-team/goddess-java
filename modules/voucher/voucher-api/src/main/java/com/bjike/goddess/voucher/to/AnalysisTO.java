package com.bjike.goddess.voucher.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 记账凭证部分数据
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 记账凭证部分数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnalysisTO extends BaseTO {

    /**
     * 分析
     */
    @NotBlank(message = "分析不能为空", groups = {ADD.class})
    private String analysis;

    /**
     * 分析类型
     */
    @NotBlank(message = "分析类型不能为空", groups = {ADD.class})
    private String analysisType;

    /**
     * 预警额
     */
    private String brow;

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public String getBrow() {
        return brow;
    }

    public void setBrow(String brow) {
        this.brow = brow;
    }
}