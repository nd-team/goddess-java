package com.bjike.goddess.devicerepair.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.devicerepair.type.AuditState;

/**
 * 福利模块审核TO
 *
 * @Author: [sunfengtao]
 * @Date: [2017-05-03 15:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WelfareAuditTO extends BaseTO {

    /**
     * 维修途径
     */
    private String repairWay;

    /**
     * 检测结果
     */
    private String detectResult;

    /**
     * 是否可维修
     */
    private Boolean whetherRepair;

    /**
     * 维修后使用期限
     */
    private String repairDeadline;

    /**
     * 维修价格
     */
    private Double repairPrice;

    /**
     * 预计维修好时间
     */
    private String planOverRepairTime;

    /**
     * 预计借款时间
     */
    private String planLoanTime;

    /**
     * 福利模块负责人审核状态
     */
    private AuditState welfareAuditState;

    /**
     * 备注
     */
    private String comment;

    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
    }

    public String getDetectResult() {
        return detectResult;
    }

    public void setDetectResult(String detectResult) {
        this.detectResult = detectResult;
    }

    public Boolean getWhetherRepair() {
        return whetherRepair;
    }

    public void setWhetherRepair(Boolean whetherRepair) {
        this.whetherRepair = whetherRepair;
    }

    public String getRepairDeadline() {
        return repairDeadline;
    }

    public void setRepairDeadline(String repairDeadline) {
        this.repairDeadline = repairDeadline;
    }

    public Double getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(Double repairPrice) {
        this.repairPrice = repairPrice;
    }

    public String getPlanOverRepairTime() {
        return planOverRepairTime;
    }

    public void setPlanOverRepairTime(String planOverRepairTime) {
        this.planOverRepairTime = planOverRepairTime;
    }

    public String getPlanLoanTime() {
        return planLoanTime;
    }

    public void setPlanLoanTime(String planLoanTime) {
        this.planLoanTime = planLoanTime;
    }

    public AuditState getWelfareAuditState() {
        return welfareAuditState;
    }

    public void setWelfareAuditState(AuditState welfareAuditState) {
        this.welfareAuditState = welfareAuditState;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
