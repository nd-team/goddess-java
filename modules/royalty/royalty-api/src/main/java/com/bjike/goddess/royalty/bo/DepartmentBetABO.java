package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 部门间对赌表A业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表A业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetABO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 分值
     */
    private Integer score;
    /**
     * 部门间对赌B
     */
    private List<DepartmentBetBBO> departmentBetBBOS;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<DepartmentBetBBO> getDepartmentBetBBOS() {
        return departmentBetBBOS;
    }

    public void setDepartmentBetBBOS(List<DepartmentBetBBO> departmentBetBBOS) {
        this.departmentBetBBOS = departmentBetBBOS;
    }
}