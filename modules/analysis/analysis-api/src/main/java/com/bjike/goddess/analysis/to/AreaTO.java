package com.bjike.goddess.analysis.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * Created by ike on 17-7-6.
 */
public class AreaTO extends BaseTO{
    public interface Collect {
    }
    /**
     * 地区
     */
    private String area;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
