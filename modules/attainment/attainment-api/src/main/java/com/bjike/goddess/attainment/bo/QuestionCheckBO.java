package com.bjike.goddess.attainment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 问卷填写信息表业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuestionCheckBO extends BaseBO {

    /**
     * 选项
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}