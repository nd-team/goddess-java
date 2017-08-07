package com.bjike.goddess.managefee.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 管理费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CollectGroupTO extends BaseTO {

    public interface TestAdd{}
    /**
     * 项目组数组
     */
    @NotNull(groups = {CollectGroupTO.TestAdd.class} , message = "项目组数组不能为空")
    private String[] projectGroups;


    /**
     * 开始时间
     */
    @NotBlank(groups = {CollectGroupTO.TestAdd.class} , message = "开始时间不能为空")
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(groups = {CollectGroupTO.TestAdd.class} , message = "结束时间不能为空")
    private String endTime;


    public String[] getProjectGroups() {
        return projectGroups;
    }

    public void setProjectGroups(String[] projectGroups) {
        this.projectGroups = projectGroups;
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