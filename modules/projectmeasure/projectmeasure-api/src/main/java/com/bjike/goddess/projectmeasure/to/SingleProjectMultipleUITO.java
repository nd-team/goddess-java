package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 单个项目多个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SingleProjectMultipleUITO extends BaseTO {

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;
    /**
     * 单项目多界面
     */
    private List<SingleProjectMultipleUIBTO> singleProjectMultipleUIBTOS;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<SingleProjectMultipleUIBTO> getSingleProjectMultipleUIBTOS() {
        return singleProjectMultipleUIBTOS;
    }

    public void setSingleProjectMultipleUIBTOS(List<SingleProjectMultipleUIBTO> singleProjectMultipleUIBTOS) {
        this.singleProjectMultipleUIBTOS = singleProjectMultipleUIBTOS;
    }
}