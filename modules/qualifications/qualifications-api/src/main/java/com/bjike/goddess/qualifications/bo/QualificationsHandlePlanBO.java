package com.bjike.goddess.qualifications.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 资质办理计划业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QualificationsHandlePlanBO extends BaseBO {

    /**
     * 资质办理
     */
    private String handle_id;


    /**
     * 准备开始时间
     */
    private String startTime;

    /**
     * 计划结束时间
     */
    private String endTime;

    /**
     * 办理方式
     */
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