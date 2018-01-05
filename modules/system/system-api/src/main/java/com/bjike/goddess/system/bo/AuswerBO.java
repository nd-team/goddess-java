package com.bjike.goddess.system.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 答案业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AuswerBO extends BaseBO {

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