package com.bjike.goddess.market.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 市场信息初步分析
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 01:36 ]
 * @Description: [ 市场信息初步分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketInfoPreAnalysisTO extends BaseTO {

    public interface testAnalysis {
    }

    public interface budgetAnalysis {
    }

    public interface planAnalysis {
    }

    /**
     * 是否为有效信息
     */
    @NotNull(message = "是否为有效日期不能为空", groups = {MarketInfoPreAnalysisTO.testAnalysis.class})
    private Boolean effctiveInfo;

    /**
     * 初步分析时间
     */
    @NotBlank(message = "初步分析时间不能为空", groups = {MarketInfoPreAnalysisTO.testAnalysis.class})
    private String preliminaryAnalyDate;

    /**
     * 是否有资金运营
     */
    @NotNull(message = "是否有资金运营不能为空", groups = {MarketInfoPreAnalysisTO.budgetAnalysis.class})
    private Boolean fundOperation;

    /**
     * 技术人员是否能够提供
     */
    @NotNull(message = "技术人员是否能够提供不能为空", groups = {MarketInfoPreAnalysisTO.planAnalysis.class})
    private Boolean ableProvide;

    /**
     * 合作方是否有合作风险
     */
    @NotNull(message = "合作方是否有合作风险不能为空", groups = {MarketInfoPreAnalysisTO.testAnalysis.class})
    private Boolean partnerRisk;

    /**
     * 风险描述
     */
    private String riskDescribe;

    /**
     * 是否进行初步分析
     */
    @NotNull(message = "是否进行初步分析不能为空", groups = {MarketInfoPreAnalysisTO.testAnalysis.class})
    private Boolean preliminaryAnaly;

    /**
     * 是否转换商机
     */
    @NotNull(message = "是否转换商机不能为空", groups = {MarketInfoPreAnalysisTO.testAnalysis.class})
    private Boolean conversionBuissOpp;

    /**
     * 是否转换为市场招待
     */
    @NotNull(message = "是否转换为市场招待不能为空", groups = {MarketInfoPreAnalysisTO.testAnalysis.class})
    private Boolean conversionMarketFor;

    /**
     * 是否转换为商务洽谈
     */
    @NotNull(message = "是否转换为商务洽谈不能为空", groups = {MarketInfoPreAnalysisTO.testAnalysis.class})
    private Boolean conversionBussNegotia;

    /**
     * 备注
     */
    private String remark;

    public Boolean getEffctiveInfo() {
        return effctiveInfo;
    }

    public void setEffctiveInfo(Boolean effctiveInfo) {
        this.effctiveInfo = effctiveInfo;
    }

    public String getPreliminaryAnalyDate() {
        return preliminaryAnalyDate;
    }

    public void setPreliminaryAnalyDate(String preliminaryAnalyDate) {
        this.preliminaryAnalyDate = preliminaryAnalyDate;
    }

    public Boolean getFundOperation() {
        return fundOperation;
    }

    public void setFundOperation(Boolean fundOperation) {
        this.fundOperation = fundOperation;
    }

    public Boolean getAbleProvide() {
        return ableProvide;
    }

    public void setAbleProvide(Boolean ableProvide) {
        this.ableProvide = ableProvide;
    }

    public Boolean getPartnerRisk() {
        return partnerRisk;
    }

    public void setPartnerRisk(Boolean partnerRisk) {
        this.partnerRisk = partnerRisk;
    }

    public String getRiskDescribe() {
        return riskDescribe;
    }

    public void setRiskDescribe(String riskDescribe) {
        this.riskDescribe = riskDescribe;
    }

    public Boolean getPreliminaryAnaly() {
        return preliminaryAnaly;
    }

    public void setPreliminaryAnaly(Boolean preliminaryAnaly) {
        this.preliminaryAnaly = preliminaryAnaly;
    }

    public Boolean getConversionBuissOpp() {
        return conversionBuissOpp;
    }

    public void setConversionBuissOpp(Boolean conversionBuissOpp) {
        this.conversionBuissOpp = conversionBuissOpp;
    }

    public Boolean getConversionMarketFor() {
        return conversionMarketFor;
    }

    public void setConversionMarketFor(Boolean conversionMarketFor) {
        this.conversionMarketFor = conversionMarketFor;
    }

    public Boolean getConversionBussNegotia() {
        return conversionBussNegotia;
    }

    public void setConversionBussNegotia(Boolean conversionBussNegotia) {
        this.conversionBussNegotia = conversionBussNegotia;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}