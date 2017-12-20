package com.bjike.goddess.organize.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-12-19 17:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class LabelBO implements Serializable {

    /**
     * 数据展示
     */
    private NormalBO normal;


    public NormalBO getNormal() {
        return normal;
    }

    public void setNormal(NormalBO normal) {
        this.normal = normal;
    }

}
