package com.bjike.goddess.annual.to;

import com.bjike.goddess.annual.enums.AuditType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 年假申请
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnnualApplyTO extends BaseTO {
    /**
     * 年假信息
     */
    @NotNull(message = "年假信息id不能为空", groups = {EDIT.class, ADD.class})
    private String infoId;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = {EDIT.class, ADD.class})
    private String startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = {EDIT.class, ADD.class})
    private String endTime;


    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
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

}