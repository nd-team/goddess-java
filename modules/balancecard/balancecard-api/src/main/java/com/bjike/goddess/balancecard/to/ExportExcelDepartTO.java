package com.bjike.goddess.balancecard.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 部门年度指标设置导出条件
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 部门年度指标设置导出条件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ExportExcelDepartTO extends BaseTO {

    /**
     * 部门
     */
    private String depart;

    /**
     * 指标类型
     */
    private String indexType;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 开始时间
     */
    private String startTime ;
    /**
     * 结束时间
     */
    private String endTime ;


    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
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
