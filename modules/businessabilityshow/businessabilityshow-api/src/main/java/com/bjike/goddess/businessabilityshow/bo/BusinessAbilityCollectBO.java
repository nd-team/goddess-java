package com.bjike.goddess.businessabilityshow.bo;

/**
 * 商务能力汇总
 * @Author: [caiwenxian]
 * @Date: [2017-12-16 16:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class BusinessAbilityCollectBO {

    /** 公司名称 */
    private String companyName;

    /** 公司资质数量 */
    private Integer qualificationAmount;

    /** 管理资质认证数量 */
    private Integer manageQualificationAmount;

    /** 专业资质认证数量 */
    private Integer professionQualificationAmount;

    /** 中兴技能认证证书数量 */
    private Integer ZXSkillCertificateAmount;

    /** 华为技能认证证书数量 */
    private Integer HWSkillCertificateAmount;

    /** 公司参与项目/已完成项目 */
    private Integer completeProjectAmount;

    /** 公司参与项目/尚在进行中的项目 */
    private Integer processingProjectAmount;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getQualificationAmount() {
        return qualificationAmount;
    }

    public void setQualificationAmount(Integer qualificationAmount) {
        this.qualificationAmount = qualificationAmount;
    }

    public Integer getManageQualificationAmount() {
        return manageQualificationAmount;
    }

    public void setManageQualificationAmount(Integer manageQualificationAmount) {
        this.manageQualificationAmount = manageQualificationAmount;
    }

    public Integer getProfessionQualificationAmount() {
        return professionQualificationAmount;
    }

    public void setProfessionQualificationAmount(Integer professionQualificationAmount) {
        this.professionQualificationAmount = professionQualificationAmount;
    }

    public Integer getZXSkillCertificateAmount() {
        return ZXSkillCertificateAmount;
    }

    public void setZXSkillCertificateAmount(Integer ZXSkillCertificateAmount) {
        this.ZXSkillCertificateAmount = ZXSkillCertificateAmount;
    }

    public Integer getHWSkillCertificateAmount() {
        return HWSkillCertificateAmount;
    }

    public void setHWSkillCertificateAmount(Integer HWSkillCertificateAmount) {
        this.HWSkillCertificateAmount = HWSkillCertificateAmount;
    }

    public Integer getCompleteProjectAmount() {
        return completeProjectAmount;
    }

    public void setCompleteProjectAmount(Integer completeProjectAmount) {
        this.completeProjectAmount = completeProjectAmount;
    }

    public Integer getProcessingProjectAmount() {
        return processingProjectAmount;
    }

    public void setProcessingProjectAmount(Integer processingProjectAmount) {
        this.processingProjectAmount = processingProjectAmount;
    }
}
