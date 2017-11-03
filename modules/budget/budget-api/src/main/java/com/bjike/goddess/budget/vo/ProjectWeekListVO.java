package com.bjike.goddess.budget.vo;

import com.bjike.goddess.budget.bo.ProjectBO;
import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 项目收入周列表业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周列表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectWeekListVO {

    /**
     * 地区
     */
    private String arrival;
    /**
     * 所属项目组数据
     */
    private List<ProjectBO> projectBOList;

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public List<ProjectBO> getProjectBOList() {
        return projectBOList;
    }

    public void setProjectBOList(List<ProjectBO> projectBOList) {
        this.projectBOList = projectBOList;
    }
}