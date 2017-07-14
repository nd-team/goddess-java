package com.bjike.goddess.interiorrecommend.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 推荐信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendInfoBO extends BaseBO {

    /**
     * 推荐时间
     */
    private String createTime;

    /**
     * 推荐目的
     */
    private String purpose;

    /**
     * 推荐类型
     */
    private String typeName;

    /**
     * 推荐人
     */
    private String recommendUser;

    /**
     * 备注
     */
    private String remark;

    /**
     * 推荐内容
     */
    private List<RecommendContentBO> contentList;

    /**
     * 是否采纳
     */
    private Boolean accept;

    /**
     * 是否符合奖励要求
     */
    private Boolean conform;

    /**
     * 原因
     */
    private String reason;

    /**
     * 推荐开通时间
     */
    private String openTime;

    /**
     * 推荐关闭时间
     */
    private String closeTime;


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRecommendUser() {
        return recommendUser;
    }

    public void setRecommendUser(String recommendUser) {
        this.recommendUser = recommendUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RecommendContentBO> getContentList() {
        return contentList;
    }

    public void setContentList(List<RecommendContentBO> contentList) {
        this.contentList = contentList;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public Boolean getConform() {
        return conform;
    }

    public void setConform(Boolean conform) {
        this.conform = conform;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}