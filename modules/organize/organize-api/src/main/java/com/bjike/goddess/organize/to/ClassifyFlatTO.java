package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-05 09:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ClassifyFlatTO extends BaseTO {

    /**
     * 专业分类
     */
    @NotBlank(message = "专业分类不能为空", groups = {ADD.class, EDIT.class})
    private String classify;


    /**
     * 工作范围集合
     */
    private List<WorkRangeListTO> workRangeListTOs;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public List<WorkRangeListTO> getWorkRangeListTOs() {
        return workRangeListTOs;
    }

    public void setWorkRangeListTOs(List<WorkRangeListTO> workRangeListTOs) {
        this.workRangeListTOs = workRangeListTOs;
    }
}
