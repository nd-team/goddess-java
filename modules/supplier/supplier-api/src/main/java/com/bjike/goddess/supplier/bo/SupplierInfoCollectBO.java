package com.bjike.goddess.supplier.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-06-12 11:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SupplierInfoCollectBO implements Serializable {

    /**
     * 地区
     */
    private String area;

    /**
     * 汇总数据
     */
    private List<SupplierInfoCollectTitleBO> titleBOs;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<SupplierInfoCollectTitleBO> getTitleBOs() {
        return titleBOs;
    }

    public void setTitleBOs(List<SupplierInfoCollectTitleBO> titleBOs) {
        this.titleBOs = titleBOs;
    }
}
