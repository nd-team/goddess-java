package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 资质办理计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QualificationsHandlePlanTO extends BaseTO {

    /**
     * 资质办理
     */
    @NotNull(message = "资质办理不能为空", groups = {ADD.class, EDIT.class})
    private String handle_id;

    /**
     * 准备开始时间
     */
    @NotNull(message = "准备开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String startTime;

    /**
     * 计划结束时间
     */
    @NotNull(message = "计划结束时间不能为空", groups = {ADD.class, EDIT.class})
    private String endTime;

    /**
     * 办理方式
     */
    @NotNull(message = "办理方式不能为空", groups = {ADD.class, EDIT.class})
    private String way;


    public String getHandle_id() {
        return handle_id;
    }

    public void setHandle_id(String handle_id) {
        this.handle_id = handle_id;
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

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}