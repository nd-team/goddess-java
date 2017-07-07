package com.bjike.goddess.quartz.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 任务调度组
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ScheduleJobGroupTO extends BaseTO {

    /**
     * 组名
     */
    @NotBlank(message = "组名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否启用
     */
    @NotNull(message = "是否启用不能为空", groups = {ADD.class, EDIT.class})
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