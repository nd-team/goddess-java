package com.bjike.goddess.bidding.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-09-23 16:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SearchTO extends BaseTO{
    /**
     * 地区
     */
    private String area;
    /**
     * 标题
     */
    private String title;
    /**
     * 公司
     */
    private String company;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
