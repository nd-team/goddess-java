package com.bjike.goddess.organize.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-08 10:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ClassifyBO implements Serializable {

    /**
     * 分类
     */
    String classify;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
