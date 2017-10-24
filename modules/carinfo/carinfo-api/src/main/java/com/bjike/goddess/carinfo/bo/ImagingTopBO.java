package com.bjike.goddess.carinfo.bo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-12 15:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ImagingTopBO {
    /**
     * x轴数据
     */
    private List<ImagingXBO> imagingXList;

    /**
     * 部门
     */
    private List<String> departmentList;

    public List<ImagingXBO> getImagingXList() {
        return imagingXList;
    }

    public void setImagingXList(List<ImagingXBO> imagingXList) {
        this.imagingXList = imagingXList;
    }

    public List<String> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<String> departmentList) {
        this.departmentList = departmentList;
    }
}
