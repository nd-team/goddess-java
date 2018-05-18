package com.bjike.goddess.contractware.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-02 17:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NormalBO extends BaseBO{
    private Boolean show;

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
