package com.bjike.goddess.attainment.to;

import com.bjike.goddess.attainment.enums.AuditType;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 调研计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SurveyPlanTO extends BaseTO {

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 需求id
     */
    private String demand_id;

    /**
     * 调研计划开始时间
     */
    private String startTime;

    /**
     * 调研计划结束时间
     */
    private String endTime;

    /**
     * 调研表制作完成时间
     */
    private String finishTime;

    /**
     * 审核状态
     */
    private AuditType audit;

    /**
     * 备注
     */
    private String remark;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDemand_id() {
        return demand_id;
    }

    public void setDemand_id(String demand_id) {
        this.demand_id = demand_id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public AuditType getAudit() {
        return audit;
    }

    public void setAudit(AuditType audit) {
        this.audit = audit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}