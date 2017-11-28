package com.bjike.goddess.taskallotment.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目列表数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:55 ]
 * @Description: [ 项目列表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectNameDTO extends BaseDTO {
    public interface QUEARY {
    }

    public interface QUEARYTABLES {
    }

    /**
     * 部门数组
     */
    @NotNull(groups = ProjectNameDTO.QUEARY.class, message = "部门数组不能为空")
    private String[] departs;

    /**
     * 开始时间
     */
    @NotBlank(groups = {ProjectNameDTO.QUEARY.class, ProjectNameDTO.QUEARYTABLES.class}, message = "开始时间不能为空")
    private String startTime;

    /**
     * 结束时间
     */
    @NotBlank(groups = {ProjectNameDTO.QUEARY.class, ProjectNameDTO.QUEARYTABLES.class}, message = "开始时间不能为空")
    private String endTime;

    /**
     * 项目数组
     */
    @NotNull(groups = ProjectNameDTO.QUEARYTABLES.class, message = "项目数组不能为空")
    private String[] projectsID;

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

    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
    }

    public String[] getProjectsID() {
        return projectsID;
    }

    public void setProjectsID(String[] projectsID) {
        this.projectsID = projectsID;
    }
}