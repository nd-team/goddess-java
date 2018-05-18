package com.bjike.goddess.attendance.dto.overtime;

import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.attendance.enums.CountType;
import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 加班数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverWorkDTO extends BaseDTO {
    public interface COUNT{}
    /**
     * 加班人员
     */
    private String overWorker;

    /**
     * 审核状态
     */
    private AuditStatus auditStatus;
    /**
     * 汇总类型
     */
    @NotNull(groups = OverWorkDTO.COUNT.class,message = "汇总类型不能为空")
    private CountType countType;
    /**
     * 开始时间
     */
    @NotBlank(groups = OverWorkDTO.COUNT.class,message = "开始时间不能为空")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(groups = OverWorkDTO.COUNT.class,message = "结束时间不能为空")
    private String endTime;
    /**
     * 部门数组
     */
    private String[] departs;

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
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

    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
    }

    public String getOverWorker() {
        return overWorker;
    }

    public void setOverWorker(String overWorker) {
        this.overWorker = overWorker;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }
}