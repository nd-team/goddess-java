package com.bjike.goddess.quartz.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 任务调度组业务传输对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ScheduleJobGroupBO extends BaseBO {

    /**
     * 组名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否启用
     */
    private Boolean enable;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}