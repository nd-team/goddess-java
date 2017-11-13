package com.bjike.goddess.taskallotment.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-11-10 10:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectDataVO extends BaseBO {

    /**
     * 姓名
     */
    private String personName;

    /**
     * 项目名称
     */
    private List<String> projectName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public List<String> getProjectName() {
        return projectName;
    }

    public void setProjectName(List<String> projectName) {
        this.projectName = projectName;
    }
}
