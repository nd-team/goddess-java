package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.organize.bo.ClassifyFlatBO;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-05 09:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectFlatTO extends BaseTO {

    /**
     * 业务方向－科目
     */
    @NotBlank(message = "业务方向－科目不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 专业分类集合
     */
    private List<ClassifyFlatTO> classifyFlatTOs;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<ClassifyFlatTO> getClassifyFlatTOs() {
        return classifyFlatTOs;
    }

    public void setClassifyFlatTOs(List<ClassifyFlatTO> classifyFlatTOs) {
        this.classifyFlatTOs = classifyFlatTOs;
    }
}
