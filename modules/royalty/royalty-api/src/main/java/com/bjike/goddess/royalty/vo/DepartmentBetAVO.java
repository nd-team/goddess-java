package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 部门间对赌表A表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表A表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetAVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 分值
     */
    private Integer score;
    /**
     * 部门间对赌表B
     */
    private List<DepartmentBetBVO> departmentBetBVOS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<DepartmentBetBVO> getDepartmentBetBVOS() {
        return departmentBetBVOS;
    }

    public void setDepartmentBetBVOS(List<DepartmentBetBVO> departmentBetBVOS) {
        this.departmentBetBVOS = departmentBetBVOS;
    }
}