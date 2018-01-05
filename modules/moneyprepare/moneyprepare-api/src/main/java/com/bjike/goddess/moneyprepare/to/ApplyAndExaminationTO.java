package com.bjike.goddess.moneyprepare.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 申请和审批
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 04:16 ]
 * @Description: [ 申请和审批 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ApplyAndExaminationTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 名称
     */
    @NotNull(groups = {ApplyAndExaminationTO.TestAdd.class} , message = "名称不能为空")
    private String tableName;

    /**
     * 申请人
     */
    private String apply;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 审批人
     */
    private String examination;

    /**
     * 审批时间
     */
    private LocalDateTime examinationTime;

    /**
     * 审批意见
     */
    private String examinationOpinion;

    /**
     * 审批状态
     */
    private boolean examinationStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 记录
     */
    private String record;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public LocalDateTime getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(LocalDateTime examinationTime) {
        this.examinationTime = examinationTime;
    }

    public String getExaminationOpinion() {
        return examinationOpinion;
    }

    public void setExaminationOpinion(String examinationOpinion) {
        this.examinationOpinion = examinationOpinion;
    }

    public boolean getExaminationStatus() {
        return examinationStatus;
    }

    public void setExaminationStatus(boolean examinationStatus) {
        this.examinationStatus = examinationStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}