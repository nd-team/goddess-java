package com.bjike.goddess.reportmanagement.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 利润增减率分析和变动分析表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-01 05:07 ]
 * @Description: [ 利润增减率分析和变动分析表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProfitFormulaVO {

    /**
     * id
     */
    private String id;

    /**
     * 说明
     */
    private String remark;

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
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 分析人
     */
    private String employeeNumber;

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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

//    /**
//     * 分析类型
//     */
//    private Integer type;




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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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