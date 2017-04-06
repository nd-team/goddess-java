package com.bjike.goddess.eggert.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 信息记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:22 ]
 * @Description: [ 信息记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecordTO extends BaseTO {

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