package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 负债表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DebtDTO extends BaseDTO {
    public interface A {
    }

    /**
     * 起始时间
     */
    @NotBlank(groups = {DebtDTO.A.class}, message = "起始时间不能为空")
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(groups = {DebtDTO.A.class}, message = "结束时间不能为空")
    private String endTime;

    /**
     * 项目名称数组
     */
    private String[] projectNames;

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