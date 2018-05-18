package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 结算进度汇总模板传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 结算进度汇总模板传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SettleProgressSummBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 统计
     */
    private Double areaStatistics;

    /**
     * 外包单位
     */
    private List<OutUnitSummBO> outUnitSummBOS;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getAreaStatistics() {
        return areaStatistics;
    }

    public void setAreaStatistics(Double areaStatistics) {
        this.areaStatistics = areaStatistics;
    }

    public List<OutUnitSummBO> getOutUnitSummBOS() {
        return outUnitSummBOS;
    }

    public void setOutUnitSummBOS(List<OutUnitSummBO> outUnitSummBOS) {
        this.outUnitSummBOS = outUnitSummBOS;
    }
}