package com.bjike.goddess.budget.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 数据业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectNameListBO extends BaseBO {

    /**
     * 内部项目名称
     */
    private String projectName;
    /**
     * 年份数据
     */
    private List<YearListBO> yearListBOList;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<YearListBO> getYearListBOList() {
        return yearListBOList;
    }

    public void setYearListBOList(List<YearListBO> yearListBOList) {
        this.yearListBOList = yearListBOList;
    }
}