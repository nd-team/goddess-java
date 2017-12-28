package com.bjike.goddess.system.vo;

/**
 * 答案表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AuswerVO {

    /**
     * id
     */
    private String id;
    /**
     * 参考答案
     */
    private String answer;

    /**
     * 提供人
     */
    private String provider;


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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}