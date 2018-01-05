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
public class CollectProjectTO extends BaseTO {

    public interface TestAdd{}
    /**
     * 项目名称数组
     */
    @NotNull(groups = {CollectProjectTO.TestAdd.class} , message = "项目名称数组不能为空")
    private String[] projectNames;


    /**
     * 开始时间
     */
    @NotBlank(groups = {CollectProjectTO.TestAdd.class} , message = "开始时间不能为空")
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(groups = {CollectProjectTO.TestAdd.class} , message = "结束时间不能为空")
    private String endTime;


    public String[] getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(String[] projectNames) {
        this.projectNames = projectNames;
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