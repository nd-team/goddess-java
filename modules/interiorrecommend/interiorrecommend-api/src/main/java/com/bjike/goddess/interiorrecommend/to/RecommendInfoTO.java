package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 推荐信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:54 ]
 * @Description: [ 推荐信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendInfoTO extends BaseTO {

    /**
     * 推荐要求设定id
     */
    private String requireId;

    /**
     * 推荐人
     */
    private String recommendUser;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否采纳
     */
    private Boolean accept;

    /**
     * 是否符合奖励要求
     */
    private Boolean conform;

    /**
     * 推荐内容
     */
    private List<RecommendContentTO> contentList;

    /**
     * 原因
     */
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRequireId() {
        return requireId;
    }

    public void setRequireId(String requireId) {
        this.requireId = requireId;
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

    public List<RecommendContentTO> getContentList() {
        return contentList;
    }

    public void setContentList(List<RecommendContentTO> contentList) {
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
}