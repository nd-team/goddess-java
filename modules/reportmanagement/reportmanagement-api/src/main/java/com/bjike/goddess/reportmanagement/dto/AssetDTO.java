package com.bjike.goddess.reportmanagement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 资产表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssetDTO extends BaseDTO {
    public interface A {
    }

    /**
     * 起始时间
     */
    @NotBlank(groups = {AssetDTO.A.class}, message = "起始时间不能为空")
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(groups = {AssetDTO.A.class}, message = "结束时间不能为空")
    private String endTime;

    /**
     * 项目组/部门
     */
//    @NotBlank(groups = {AssetDTO.A.class}, message = "项目组/部门不能为空")
    private String projectGroup;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
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