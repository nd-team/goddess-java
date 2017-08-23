package com.bjike.goddess.eggert.bo;

import com.bjike.goddess.common.api.bo.BaseBO;


/**
 * 信息记录业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:22 ]
 * @Description: [ 信息记录业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecordBO extends BaseBO {

    /**
     * 答案
     */
    private String answer;


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}