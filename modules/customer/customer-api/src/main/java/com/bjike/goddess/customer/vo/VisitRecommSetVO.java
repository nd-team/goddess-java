package com.bjike.goddess.customer.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.enums.RecommInfoUpdateFreq;
import com.bjike.goddess.customer.enums.ReminderVisit;

/**
 * 拜访推荐设置表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 04:12 ]
 * @Description: [ 拜访推荐设置表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VisitRecommSetVO {

    /**
     * id
     */
    private String id;
    /**
     * 创建/修改人
     */
    private String createPersion;

    /**
     * 更新时间
     */
    private String updateDate;

    /**
     * 推荐更新信息时间
     */
    private String recommInfoUpdateTime;
    /**
     * 推荐信息更新频率
     */
    private RecommInfoUpdateFreq recommInfoUpdateFreq;


    /**
     * 推荐间隔
     */
    private RecommInfoUpdateFreq recommendInterval;
    /**
     * 推荐时间
     */
    private String recommendDate;

    /**
     * 推荐提醒时间
     */
    private ReminderVisit reminderVisit;

    /**
     * 发送对象
     */
    private String sendObject;

    /**
     * 状态
     */
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatePersion() {
        return createPersion;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRecommInfoUpdateTime() {
        return recommInfoUpdateTime;
    }

    public void setRecommInfoUpdateTime(String recommInfoUpdateTime) {
        this.recommInfoUpdateTime = recommInfoUpdateTime;
    }

    public RecommInfoUpdateFreq getRecommInfoUpdateFreq() {
        return recommInfoUpdateFreq;
    }

    public void setRecommInfoUpdateFreq(RecommInfoUpdateFreq recommInfoUpdateFreq) {
        this.recommInfoUpdateFreq = recommInfoUpdateFreq;
    }

    public RecommInfoUpdateFreq getRecommendInterval() {
        return recommendInterval;
    }

    public void setRecommendInterval(RecommInfoUpdateFreq recommendInterval) {
        this.recommendInterval = recommendInterval;
    }

    public String getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(String recommendDate) {
        this.recommendDate = recommendDate;
    }

    public ReminderVisit getReminderVisit() {
        return reminderVisit;
    }

    public void setReminderVisit(ReminderVisit reminderVisit) {
        this.reminderVisit = reminderVisit;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}