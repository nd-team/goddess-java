package com.bjike.goddess.eggert.vo;

/**
 * 信息记录表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:22 ]
 * @Description: [ 信息记录表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecordVO {

    /**
     * id
     */
    private String id;
    /**
     * 答案
     */
    private String answer;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}