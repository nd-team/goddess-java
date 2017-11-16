package com.bjike.goddess.taskallotment.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

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
     * 项目数组
     */
    @NotNull(groups = ProjectNameDTO.QUEARYTABLES.class, message = "项目数组不能为空")
    private String[] projects;


    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
    }

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
    }
}