package com.bjike.goddess.workhandover.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 工作交接时间规范
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-11 04:47 ]
 * @Description: [ 工作交接时间规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class workTimeSpecificationExport extends BaseEntity {

    /**
     * 工作交接原因
     */
    @ExcelHeader(name = "工作交接原因")
    private String workHandoverReason;

    /**
     * 交接流程
     */
    @ExcelHeader(name = "交接流程")
    private String workHandoverProcess;

    /**
     * 对象
     */
    @ExcelHeader(name = "对象")
    private String objects;

    /**
     * 交接时间
     */
    @ExcelHeader(name = "交接时间")
    private LocalDate handoverTime;

    public String getWorkHandoverProcess() {
        return workHandoverProcess;
    }

    public void setWorkHandoverProcess(String workHandoverProcess) {
        this.workHandoverProcess = workHandoverProcess;
    }

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects;
    }

    public LocalDate getHandoverTime() {
        return handoverTime;
    }

    public void setHandoverTime(LocalDate handoverTime) {
        this.handoverTime = handoverTime;
    }

    public String getWorkHandoverReason() {
        return workHandoverReason;
    }

    public void setWorkHandoverReason(String workHandoverReason) {
        this.workHandoverReason = workHandoverReason;
    }
}