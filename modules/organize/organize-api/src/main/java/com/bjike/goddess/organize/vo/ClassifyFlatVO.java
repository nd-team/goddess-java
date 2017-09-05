package com.bjike.goddess.organize.vo;

import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-05 09:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ClassifyFlatVO {

    /**
     * 专业分类
     */
    private String classify;


    /**
     * 工作范围集合
     */
    private List<WorkRangeListVO> workRangeListVOs;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public List<WorkRangeListVO> getWorkRangeListVOs() {
        return workRangeListVOs;
    }

    public void setWorkRangeListVOs(List<WorkRangeListVO> workRangeListVOs) {
        this.workRangeListVOs = workRangeListVOs;
    }
}
