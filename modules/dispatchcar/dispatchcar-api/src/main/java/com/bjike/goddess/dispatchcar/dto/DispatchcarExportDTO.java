package com.bjike.goddess.dispatchcar.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.dispatchcar.enums.FindType;

/**
 * 导出条件
 * @Author: [caiwenxian]
 * @Date: [2018-01-31 11:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DispatchcarExportDTO extends BaseDTO{

    /**
     * 数据状态
     */
    private FindType findType;

    /**
     * 导出开始时间
     */
    private String startTime;

    /**
     * 导出结束时间
     */
    private String endTime;

    /**
     * 地区
     */
    private String area;



    public DispatchcarExportDTO(FindType findType, String startTime, String endTime) {
        this.findType = findType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DispatchcarExportDTO() {
    }

    @Override
    public String toString() {
        return "DispatchcarExportDTO{" +
                "findType=" + findType +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public FindType getFindType() {
        return findType;
    }

    public void setFindType(FindType findType) {
        this.findType = findType;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
