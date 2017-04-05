package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 项目费用
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 02:17 ]
 * @Description: [ 项目费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectCostTO extends BaseTO {

    /**
     * 服务费用
     */
    private Double serviceCost;

    /**
     * 招待费用
     */
    private Double entertainCost;

    /**
     * 提成
     */
    private Double commission;

    /**
     * 其它
     */
    private Double another;

    /**
     * 项目信息Id
     */
    private String projectInfoId;


    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Double getEntertainCost() {
        return entertainCost;
    }

    public void setEntertainCost(Double entertainCost) {
        this.entertainCost = entertainCost;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getAnother() {
        return another;
    }

    public void setAnother(Double another) {
        this.another = another;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}