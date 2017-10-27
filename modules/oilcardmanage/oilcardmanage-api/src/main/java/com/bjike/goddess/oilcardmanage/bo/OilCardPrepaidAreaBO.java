package com.bjike.goddess.oilcardmanage.bo;

import java.util.List;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-16 09:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardPrepaidAreaBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 部门集合
     */
    private List<OilCardPrepaidDepartmentBO> oilCardPrepaidDepartmentList;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<OilCardPrepaidDepartmentBO> getOilCardPrepaidDepartmentList() {
        return oilCardPrepaidDepartmentList;
    }

    public void setOilCardPrepaidDepartmentList(List<OilCardPrepaidDepartmentBO> oilCardPrepaidDepartmentList) {
        this.oilCardPrepaidDepartmentList = oilCardPrepaidDepartmentList;
    }
}
