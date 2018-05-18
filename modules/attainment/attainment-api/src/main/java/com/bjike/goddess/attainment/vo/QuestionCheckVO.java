package com.bjike.goddess.attainment.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 问卷填写信息表业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuestionCheckVO {

    /**
     * 问题选项id
     */
    private String id;

    /**
     * 选项
     */
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}