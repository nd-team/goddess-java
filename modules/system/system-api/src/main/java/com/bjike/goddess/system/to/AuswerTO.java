package com.bjike.goddess.system.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 答案
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AuswerTO extends BaseTO {

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