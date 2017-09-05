package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-05 09:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectFlatBO extends BaseBO {

    /**
     * 业务方向－科目
     */
    private String project;

    /**
     * 专业分类集合
     */
    private List<ClassifyFlatBO> classifyFlatBOs;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<ClassifyFlatBO> getClassifyFlatBOs() {
        return classifyFlatBOs;
    }

    public void setClassifyFlatBOs(List<ClassifyFlatBO> classifyFlatBOs) {
        this.classifyFlatBOs = classifyFlatBOs;
    }
}
