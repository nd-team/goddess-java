package com.bjike.goddess.feedback.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 其他模块意见业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:48 ]
 * @Description: [ 其他模块意见业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class OtherIdeaBO extends BaseBO {

    /**
     * 模块
     */
    private String module;

    /**
     * 意见
     */
    private String idea;


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}