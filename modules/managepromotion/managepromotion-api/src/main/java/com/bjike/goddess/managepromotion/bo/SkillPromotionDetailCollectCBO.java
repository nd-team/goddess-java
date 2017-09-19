package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 技能晋升明细汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 14:24]
 * @Description: [ 技能晋升明细汇总]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SkillPromotionDetailCollectCBO extends BaseBO {


    /**
     * 姓名
     */
    private String name;

    /**
     * 岗位
     */
    private String jobs;


    /**
     * 渠道
     */
    private String channel;

    /**
     * 时间
     */
    private String times;

    /**
     * 晋升前（不包括各项补助）
     */
    private Integer promotionBefore;

    /**
     * 晋升后（不包括各项补助）
     */
    private Integer promotionAfter;

    /**
     * 幅度
     */
    private Integer extent;

    /**
     * 是否通过
     */
    private String pass;

    /**
     * 备注
     */
    private String remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getPromotionBefore() {
        return promotionBefore;
    }

    public void setPromotionBefore(Integer promotionBefore) {
        this.promotionBefore = promotionBefore;
    }

    public Integer getPromotionAfter() {
        return promotionAfter;
    }

    public void setPromotionAfter(Integer promotionAfter) {
        this.promotionAfter = promotionAfter;
    }

    public Integer getExtent() {
        return extent;
    }

    public void setExtent(Integer extent) {
        this.extent = extent;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
