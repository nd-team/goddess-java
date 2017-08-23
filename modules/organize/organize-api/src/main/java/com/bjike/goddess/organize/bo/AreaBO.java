package com.bjike.goddess.organize.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-08 10:08]
 * @Description: [ 地区传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaBO implements Serializable {

    /**
     * 地区
     */
    String area;

    public AreaBO(String area) {
        this.area = area;
    }

    public AreaBO() {

    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
