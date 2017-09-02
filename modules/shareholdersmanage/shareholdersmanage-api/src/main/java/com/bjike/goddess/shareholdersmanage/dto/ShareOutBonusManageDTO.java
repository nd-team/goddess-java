package com.bjike.goddess.shareholdersmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 分红管理数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:47 ]
 * @Description: [ 分红管理数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOutBonusManageDTO extends BaseDTO {
    /**
     * 分红日期开始时间
     */
    private String startDate;
    /**
     * 分红日期结束时间
     */
    private String endDate;
    /**
     * 地区
     */
    private String area;

    /**
     * 股东姓名
     */
    private String shareholderName;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }
}