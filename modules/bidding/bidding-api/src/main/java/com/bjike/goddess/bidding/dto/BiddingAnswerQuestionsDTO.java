package com.bjike.goddess.bidding.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 投标答疑问题记录数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:54:11.891 ]
 * @Description: [ 投标答疑问题记录数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingAnswerQuestionsDTO extends BaseDTO {
    /**
     * 项目名称
     */
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}