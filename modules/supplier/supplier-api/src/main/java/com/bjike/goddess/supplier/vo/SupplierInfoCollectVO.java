package com.bjike.goddess.supplier.vo;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-06-12 11:44]
 * @Description: [  ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SupplierInfoCollectVO {

    /**
     * 地区
     */
    private String area;

    /**
     * 汇总数据
     */
    private List<SupplierInfoCollectTitleVO> titleVOs;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<SupplierInfoCollectTitleVO> getTitleVOs() {
        return titleVOs;
    }

    public void setTitleVOs(List<SupplierInfoCollectTitleVO> titleVOs) {
        this.titleVOs = titleVOs;
    }
}
