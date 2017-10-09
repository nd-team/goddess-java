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
public class ProjectDTO extends BaseDTO {
    public interface DEPART{}
    public interface PROJECT{}
    /**
     * 地区数组
     */
    @NotNull(groups = ProjectDTO.DEPART.class,message = "地区数组不能为空")
    private String[] areas;
    /**
     * 部门数组
     */
    @NotNull(groups = ProjectDTO.PROJECT.class,message = "部门数组不能为空")
    private String[] departs;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String[] getDeparts() {
        return departs;
    }

    public void setDeparts(String[] departs) {
        this.departs = departs;
    }
}