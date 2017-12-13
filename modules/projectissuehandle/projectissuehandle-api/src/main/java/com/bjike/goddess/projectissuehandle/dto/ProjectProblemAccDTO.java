package com.bjike.goddess.projectissuehandle.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 项目中问题受理和处理数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-08 03:43 ]
 * @Description: [ 项目中问题受理和处理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectProblemAccDTO extends BaseDTO {
    /**
     * 问题编号
     */
    private String questionNum;

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }
}