package com.bjike.goddess.marketactivitymanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 市场招待记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.065 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryTO extends BaseTO {

    /**
     * 项目名称
     */
    private String[] projectGroups;
    /**
     * 开始时间
     */
    private String startTimeString;
    /**
     * 结束时间
     */
    private String endTimeString;

    /**
     * 类型
     */
    private Boolean type;

    public String[] getProjectGroups() {
        return projectGroups;
    }

    public void setProjectGroups(String[] projectGroups) {
        this.projectGroups = projectGroups;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}