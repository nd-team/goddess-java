package com.bjike.goddess.carinfo.bo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-12 15:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ImagingXBO {

    /**
     * x轴部门名称
     */
    private String name;

    /**
     * 汇总数据
     */
    private List<ImagingBO> imagingList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImagingBO> getImagingList() {
        return imagingList;
    }

    public void setImagingList(List<ImagingBO> imagingList) {
        this.imagingList = imagingList;
    }
}
