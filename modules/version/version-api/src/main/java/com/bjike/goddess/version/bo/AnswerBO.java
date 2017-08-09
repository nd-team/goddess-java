package com.bjike.goddess.version.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 答案业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:10 ]
 * @Description: [ 答案业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AnswerBO extends BaseBO {

    /**
     * 参考答案
     */
    private String answer;

    /**
     * 提供人
     */
    private String provider;


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}