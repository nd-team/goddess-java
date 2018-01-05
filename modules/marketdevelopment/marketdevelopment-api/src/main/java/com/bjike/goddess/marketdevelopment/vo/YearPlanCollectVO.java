package com.bjike.goddess.marketdevelopment.vo;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-06-16 09:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class YearPlanCollectVO {

    /**
     * 年
     */
    private Integer year;

    /**
     * 业务类型
     */
    private String type;

    /**
     * 工作量权重
     */
    private String workloadWeight;

    /**
     * 业务可发展对象
     */
    private Double development;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkloadWeight() {
        return workloadWeight;
    }

    public void setWorkloadWeight(String workloadWeight) {
        this.workloadWeight = workloadWeight;
    }

    public Double getDevelopment() {
        return development;
    }

    public void setDevelopment(Double development) {
        this.development = development;
    }
}
