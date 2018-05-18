package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 利润增减率分析和变动分析
 *
 * @Author: [ 董添添 ]
 * @Date: [ 2017-11-01 05:07 ]
 * @Description: [ 利润增减率分析和变动分析 ]对应前端传入的参数
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitFormulaTO extends BaseTO {

    /**
     * 净利润分析
     */
    private String netProfitAnalysis;

    /**
     * 利润总额分析
     *
     */
    private String totalProfitAnalysis;

    /**
     * 营业利润分析
     */
    private String businessProfitAnalysis;

    /**
     * 分析结果及建议
     */
    private String analysisResultSuggestions;

    /**
     * 说明
     */
    private String remark;

    /**
     * 变动情况分析
     * @return
     */
    private String changeAnalysis;

    /**
     * 变动情况建议
     * @return
     */
    private String changeAdvice;

    public String getChangeAnalysis() {
        return changeAnalysis;
    }

    public void setChangeAnalysis(String changeAnalysis) {
        this.changeAnalysis = changeAnalysis;
    }

    public String getChangeAdvice() {
        return changeAdvice;
    }

    public void setChangeAdvice(String changeAdvice) {
        this.changeAdvice = changeAdvice;
    }

    //    /**
//     * 分析类型
//     */

    public String getNetProfitAnalysis() {
        return netProfitAnalysis;
    }

    public String getTotalProfitAnalysis() {
        return totalProfitAnalysis;
    }

    public String getBusinessProfitAnalysis() {
        return businessProfitAnalysis;
    }

    public String getAnalysisResultSuggestions() {
        return analysisResultSuggestions;
    }

    public void setNetProfitAnalysis(String netProfitAnalysis) {
        this.netProfitAnalysis = netProfitAnalysis;
    }

    public void setTotalProfitAnalysis(String totalProfitAnalysis) {
        this.totalProfitAnalysis = totalProfitAnalysis;
    }

    public void setBusinessProfitAnalysis(String businessProfitAnalysis) {
        this.businessProfitAnalysis = businessProfitAnalysis;
    }

    public void setAnalysisResultSuggestions(String analysisResultSuggestions) {
        this.analysisResultSuggestions = analysisResultSuggestions;
    }
//    private Integer type;


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
}