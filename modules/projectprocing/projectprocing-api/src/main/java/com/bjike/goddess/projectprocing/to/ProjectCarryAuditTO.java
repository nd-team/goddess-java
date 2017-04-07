package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 项目实施审核(针对没派工单情况)
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 06:41 ]
 * @Description: [ 项目实施审核(针对没派工单情况) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectCarryAuditTO extends BaseTO {

    public interface TESTAddAndEdit{}

    /**
     * 合同外部项目名称
     */
    @NotBlank(groups = ProjectSituationTO.TESTAddAndEdit.class,message = "合同外部项目名称不能为空")
    private String outerName;

    /**
     * 内部项目名称
     */
    @NotBlank(groups = ProjectSituationTO.TESTAddAndEdit.class,message = "内部项目名称不能为空")
    private String innerName;

    /**
     * 销售合同号
     */
    @NotBlank(groups = ProjectSituationTO.TESTAddAndEdit.class,message = "销售合同号不能为空")
    private String saleNum;

    /**
     * 立项情况
     */
    @NotBlank(groups = ProjectSituationTO.TESTAddAndEdit.class,message = "立项情况不能为空")
    private String signProjectCondition;

    /**
     * 工作内容
     */
    private String workContent;

    /**
     * 规划模块审核意见
     */
    private String planModuleAudit;

    /**
     * 预算模块审核意见
     */
    private String budgetModuleAudit;

    /**
     * 商务决策层审核意见
     */
    private String businessModuleAudit;

    /**
     * 总经办审核意见
     */
    private String manageModuleAudit;

    /**
     * 是否实施
     */
    private String carryCondition;

    /**
     * 催派工单进度
     */
    private String dispatchProcing;

    /**
     * 商务合同外部项目名称id
     */
    private String outerNameId;

    /**
     * 市场信息记录内部项目名称id
     */
    private String innerNameId;

    /**
     * 商务项目合同基本信息对应销售合同号id
     */
    private String saleNumId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getOuterName() {
        return outerName;
    }

    public void setOuterName(String outerName) {
        this.outerName = outerName;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getSignProjectCondition() {
        return signProjectCondition;
    }

    public void setSignProjectCondition(String signProjectCondition) {
        this.signProjectCondition = signProjectCondition;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getPlanModuleAudit() {
        return planModuleAudit;
    }

    public void setPlanModuleAudit(String planModuleAudit) {
        this.planModuleAudit = planModuleAudit;
    }

    public String getBudgetModuleAudit() {
        return budgetModuleAudit;
    }

    public void setBudgetModuleAudit(String budgetModuleAudit) {
        this.budgetModuleAudit = budgetModuleAudit;
    }

    public String getBusinessModuleAudit() {
        return businessModuleAudit;
    }

    public void setBusinessModuleAudit(String businessModuleAudit) {
        this.businessModuleAudit = businessModuleAudit;
    }

    public String getManageModuleAudit() {
        return manageModuleAudit;
    }

    public void setManageModuleAudit(String manageModuleAudit) {
        this.manageModuleAudit = manageModuleAudit;
    }

    public String getCarryCondition() {
        return carryCondition;
    }

    public void setCarryCondition(String carryCondition) {
        this.carryCondition = carryCondition;
    }

    public String getDispatchProcing() {
        return dispatchProcing;
    }

    public void setDispatchProcing(String dispatchProcing) {
        this.dispatchProcing = dispatchProcing;
    }

    public String getOuterNameId() {
        return outerNameId;
    }

    public void setOuterNameId(String outerNameId) {
        this.outerNameId = outerNameId;
    }

    public String getInnerNameId() {
        return innerNameId;
    }

    public void setInnerNameId(String innerNameId) {
        this.innerNameId = innerNameId;
    }

    public String getSaleNumId() {
        return saleNumId;
    }

    public void setSaleNumId(String saleNumId) {
        this.saleNumId = saleNumId;
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
}