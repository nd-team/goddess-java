package com.bjike.goddess.oilcardmanage.vo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-16 09:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardPrepaidAreaVO {

    /**
     * 地区
     */
    private String area;

    /**
     * 部门集合
     */
    private List<OilCardPrepaidDepartmentVO> oilCardPrepaidDepartmentList;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<OilCardPrepaidDepartmentVO> getOilCardPrepaidDepartmentList() {
        return oilCardPrepaidDepartmentList;
    }

    public void setOilCardPrepaidDepartmentList(List<OilCardPrepaidDepartmentVO> oilCardPrepaidDepartmentList) {
        this.oilCardPrepaidDepartmentList = oilCardPrepaidDepartmentList;
    }
}
