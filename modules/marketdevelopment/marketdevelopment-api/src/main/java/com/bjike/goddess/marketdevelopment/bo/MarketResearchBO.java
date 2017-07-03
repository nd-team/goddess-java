package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 市场调研业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:16 ]
 * @Description: [ 市场调研业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketResearchBO extends BaseBO {

    /**
     * 业务类型
     */
    private String type;

    /**
     * 业务方向科目
     */
    private String course;

    /**
     * 业务对象
     */
    private String business;

    /**
     * 竞争对手名称
     */
    private String competitors;

    /**
     * 价格评估
     */
    private String evaluate;

    /**
     * 备注
     */
    private String remark;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCompetitors() {
        return competitors;
    }

    public void setCompetitors(String competitors) {
        this.competitors = competitors;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}