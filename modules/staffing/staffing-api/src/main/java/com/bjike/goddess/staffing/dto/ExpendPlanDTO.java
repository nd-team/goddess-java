package com.bjike.goddess.staffing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 人工成本计划数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:47 ]
 * @Description: [ 人工成本计划数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExpendPlanDTO extends BaseDTO {
    public interface FIND{}
    /**
     * 时间
     */
    @NotBlank(groups = ExpendPlanDTO.FIND.class,message = "时间不能为空")
    private String time;
    /**
     * 项目组
     */
    @NotBlank(groups = ExpendPlanDTO.FIND.class,message = "项目组不能为空")
    private String project;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}