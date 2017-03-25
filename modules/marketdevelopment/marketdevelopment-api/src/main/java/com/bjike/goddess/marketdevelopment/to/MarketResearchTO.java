package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 市场调研
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:16 ]
 * @Description: [ 市场调研 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketResearchTO extends BaseTO {

    /**
     * 业务类型
     */
    @NotNull(message = "业务类型不能为空",groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 业务方向科目
     */
    @NotNull(message = "业务方向科目不能为空",groups = {ADD.class, EDIT.class})
    private String course;

    /**
     * 业务对象
     */
    @NotNull(message = "业务对象不能为空",groups = {ADD.class, EDIT.class})
    private String business;

    /**
     * 竞争对手名称
     */
    @NotNull(message = "竞争对手名称不能为空",groups = {ADD.class, EDIT.class})
    private String competitors;

    /**
     * 价格评估
     */
    @NotNull(message = "价格评估不能为空",groups = {ADD.class, EDIT.class})
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