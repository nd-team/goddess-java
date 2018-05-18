package com.bjike.goddess.organize.vo;

import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-05 09:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectFlatVO {

    /**
     * 业务方向－科目
     */
    private String project;

    /**
     * 专业分类集合
     */
    private List<ClassifyFlatVO> classifyFlatVOs;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<ClassifyFlatVO> getClassifyFlatVOs() {
        return classifyFlatVOs;
    }

    public void setClassifyFlatVOs(List<ClassifyFlatVO> classifyFlatVOs) {
        this.classifyFlatVOs = classifyFlatVOs;
    }
}
