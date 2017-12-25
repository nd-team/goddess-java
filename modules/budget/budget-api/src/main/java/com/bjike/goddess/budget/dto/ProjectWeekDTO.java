package com.bjike.goddess.budget.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目收入周数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectWeekDTO extends BaseDTO {
    /**
     * 项目数组
     */
    private String[] projects;

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
    }
}