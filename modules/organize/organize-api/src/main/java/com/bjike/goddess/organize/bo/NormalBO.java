package com.bjike.goddess.organize.bo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-12-19 17:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NormalBO implements Serializable {

    /**
     * 是否展示数据名称
     */
    private Boolean show;

    /**
     * 展示数据的位置
     */
    private String position;

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
