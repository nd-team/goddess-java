package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 部门间对赌表A
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表A ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentBetATO extends BaseTO {
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
    private List<DepartmentBetBTO> departmentBetBTOS;

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

    public List<DepartmentBetBTO> getDepartmentBetBTOS() {
        return departmentBetBTOS;
    }

    public void setDepartmentBetBTOS(List<DepartmentBetBTO> departmentBetBTOS) {
        this.departmentBetBTOS = departmentBetBTOS;
    }
}