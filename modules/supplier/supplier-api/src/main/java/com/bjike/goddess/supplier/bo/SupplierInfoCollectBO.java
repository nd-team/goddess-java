package com.bjike.goddess.supplier.bo;

import java.io.Serializable;
import java.util.Set;

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
    private Set<SupplierInfoCollectTitleBO> titleBOs;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Set<SupplierInfoCollectTitleBO> getTitleBOs() {
        return titleBOs;
    }

    public void setTitleBOs(Set<SupplierInfoCollectTitleBO> titleBOs) {
        this.titleBOs = titleBOs;
    }
}
