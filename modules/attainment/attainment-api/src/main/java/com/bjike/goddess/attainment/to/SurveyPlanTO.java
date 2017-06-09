package com.bjike.goddess.attainment.to;

import com.bjike.goddess.attainment.enums.AuditType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "编号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 需求id
     */
    @NotNull(message = "需求id不能为空", groups = {ADD.class, EDIT.class})
    private String demandId;

    /**
     * 调研计划开始时间
     */
    @NotNull(message = "调研计划开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String startTime;

    /**
     * 调研计划结束时间
     */
    @NotNull(message = "调研计划结束时间不能为空", groups = {ADD.class, EDIT.class})
    private String endTime;

    /**
     * 调研表制作完成时间
     */
    @NotNull(message = "调研表制作完成时间不能为空", groups = {ADD.class, EDIT.class})
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

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
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