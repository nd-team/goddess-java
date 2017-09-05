package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-05 09:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ClassifyFlatBO extends BaseBO {

    /**
     * 专业分类
     */
    private String classify;


    /**
     * 工作范围集合
     */
    private List<WorkRangeListBO> workRangeListBOs;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public List<WorkRangeListBO> getWorkRangeListBOs() {
        return workRangeListBOs;
    }

    public void setWorkRangeListBOs(List<WorkRangeListBO> workRangeListBOs) {
        this.workRangeListBOs = workRangeListBOs;
    }
}
