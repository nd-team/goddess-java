package com.bjike.goddess.budget.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 所属项目列表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 所属项目列表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectBO extends BaseBO {

    /**
     * 所属项目组
     */
    private String project;
    /**
     * 内部项目名称数据
     */
    private List<ProjectNameListBO> projectNameListBOList;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<ProjectNameListBO> getProjectNameListBOList() {
        return projectNameListBOList;
    }

    public void setProjectNameListBOList(List<ProjectNameListBO> projectNameListBOList) {
        this.projectNameListBOList = projectNameListBOList;
    }
}