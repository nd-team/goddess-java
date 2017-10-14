package com.bjike.goddess.attendance.dto.overtime;

import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 剩余加班数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 剩余加班数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OverWorkRestDayDTO extends BaseDTO {

    /**
     * 加班人员名
     */
    private String overWorker;

    /**
     * 核算截至时间(年月)
     */
    private String checkEndTime;


    public String getOverWorker() {
        return overWorker;
    }

    public void setOverWorker(String overWorker) {
        this.overWorker = overWorker;
    }

    public String getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(String checkEndTime) {
        this.checkEndTime = checkEndTime;
    }
}