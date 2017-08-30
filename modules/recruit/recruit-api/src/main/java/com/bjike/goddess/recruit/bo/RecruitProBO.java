package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.recruit.type.AuditType;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-09 16:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitProBO extends BaseBO {
    /**
     * 招聘网站
     */
    private String recruitSite;

    /**
     * 套餐名称
     */
    private String name;

    /**
     * 招聘套餐费用
     */
    private Double cost;

    /**
     * 套餐时效
     */
    private String aging;

    /**
     * 招聘套餐可发布岗位数
     */
    private String postCount;

    /**
     * 招聘套餐可下载简历数
     */
    private String downloadCount;

    /**
     * 广告和推广时间
     */
    private String anAndExpandTime;

    /**
     * 招聘效果分析
     */
    private String effectAnalyze;

    /**
     * 申请套餐原因
     */
    private String applyReason;

    /**
     * 综合资源部意见
     */
    private String zhOpinion;

    /**
     * 运营商务部意见
     */
    private String yyOpinion;

    /**
     * 总经办审核意见
     */
    private String zjbOpnion;

    /**
     * 是否签订合同
     */
    private Boolean haveContract;

    /**
     * 签订合同日期
     */
    private String contractDate;

    /**
     * 合同开始日期
     */
    private String contractStartDate;

    /**
     * 是否有合同附件
     */
    private Boolean haveAnnex;

    /**
     * 是否付款
     */
    private Boolean whetherPay;

    /**
     * 付款日期
     */
    private String payDate;

    /**
     * 付款金额
     */
    private Double payCount;

    /**
     * 是否有发票
     */
    private Boolean haveInvoice;

    /**
     * 备注
     */
    private String note;

    /**
     * 审核状态
     */
    private AuditType auditType;

    public AuditType getAuditType() {
        return auditType;
    }

    public void setAuditType(AuditType auditType) {
        this.auditType = auditType;
    }

    public String getRecruitSite() {
        return recruitSite;
    }

    public void setRecruitSite(String recruitSite) {
        this.recruitSite = recruitSite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getAging() {
        return aging;
    }

    public void setAging(String aging) {
        this.aging = aging;
    }

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getAnAndExpandTime() {
        return anAndExpandTime;
    }

    public void setAnAndExpandTime(String anAndExpandTime) {
        this.anAndExpandTime = anAndExpandTime;
    }

    public String getEffectAnalyze() {
        return effectAnalyze;
    }

    public void setEffectAnalyze(String effectAnalyze) {
        this.effectAnalyze = effectAnalyze;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getZhOpinion() {
        return zhOpinion;
    }

    public void setZhOpinion(String zhOpinion) {
        this.zhOpinion = zhOpinion;
    }

    public String getYyOpinion() {
        return yyOpinion;
    }

    public void setYyOpinion(String yyOpinion) {
        this.yyOpinion = yyOpinion;
    }

    public String getZjbOpnion() {
        return zjbOpnion;
    }

    public void setZjbOpnion(String zjbOpnion) {
        this.zjbOpnion = zjbOpnion;
    }

    public Boolean getHaveContract() {
        return haveContract;
    }

    public void setHaveContract(Boolean haveContract) {
        this.haveContract = haveContract;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Boolean getHaveAnnex() {
        return haveAnnex;
    }

    public void setHaveAnnex(Boolean haveAnnex) {
        this.haveAnnex = haveAnnex;
    }

    public Boolean getWhetherPay() {
        return whetherPay;
    }

    public void setWhetherPay(Boolean whetherPay) {
        this.whetherPay = whetherPay;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public Double getPayCount() {
        return payCount;
    }

    public void setPayCount(Double payCount) {
        this.payCount = payCount;
    }

    public Boolean getHaveInvoice() {
        return haveInvoice;
    }

    public void setHaveInvoice(Boolean haveInvoice) {
        this.haveInvoice = haveInvoice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
