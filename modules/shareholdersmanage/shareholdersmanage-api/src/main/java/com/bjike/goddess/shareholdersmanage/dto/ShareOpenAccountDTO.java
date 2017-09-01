package com.bjike.goddess.shareholdersmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 股东开户数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ShareOpenAccountDTO extends BaseDTO {
    /**
     * 开户日期开始时间
     */
    private String startDate;
    /**
     * 开户日期结束时间
     */
    private String endDate;
    /**
     * 地区
     */
    private String area;

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
}