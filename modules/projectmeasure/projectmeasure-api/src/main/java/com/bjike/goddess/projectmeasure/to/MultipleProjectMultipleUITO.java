package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 多项目多个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultipleProjectMultipleUITO extends BaseTO {

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String projectName;

    /**
     * 多项目多界面
     */
    private List<MultipleProjectMultipleUIBTO> multipleProjectMultipleUIBTOS;

    public List<MultipleProjectMultipleUIBTO> getMultipleProjectMultipleUIBTOS() {
        return multipleProjectMultipleUIBTOS;
    }

    public void setMultipleProjectMultipleUIBTOS(List<MultipleProjectMultipleUIBTO> multipleProjectMultipleUIBTOS) {
        this.multipleProjectMultipleUIBTOS = multipleProjectMultipleUIBTOS;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


}