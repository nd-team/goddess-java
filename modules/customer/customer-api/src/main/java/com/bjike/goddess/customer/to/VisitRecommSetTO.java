package com.bjike.goddess.customer.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.customer.enums.RecommInfoUpdateFreq;
import com.bjike.goddess.customer.enums.ReminderVisit;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 拜访推荐设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 04:12 ]
 * @Description: [ 拜访推荐设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VisitRecommSetTO extends BaseTO {

    /**
     * 推荐更新信息时间
     */
    @NotBlank(message = "推荐更新信息时间不能为空",groups = {ADD.class,EDIT.class})
    private String RecommInfoUpdateTime;
    /**
     * 推荐信息更新频率
     */
    @NotNull(message = "推荐信息更新频率不能为空",groups = {ADD.class,EDIT.class})
    private RecommInfoUpdateFreq recommInfoUpdateFreq;
    /**
     * 推荐间隔
     */
    @NotNull(message = "推荐间隔不能为空",groups = {ADD.class,EDIT.class})
    private RecommInfoUpdateFreq recommendInterval;
    /**
     * 推荐提醒时间
     */
    @NotNull(message = "推荐提醒时间不能为空",groups = {ADD.class,EDIT.class})
    private ReminderVisit reminderVisit;
    /**
     * 推荐时间
     */
    @NotBlank(message = "推荐时间不能为空",groups = {ADD.class,EDIT.class})
    private String recommendDate;

    /**
     * 发送对象
     */
    @NotNull(message = "发送对象不能为空",groups = {ADD.class,EDIT.class})
    private List<String> sendObject;

    /**
     * 状态
     */
    private Status status;

    public String getRecommInfoUpdateTime() {
        return RecommInfoUpdateTime;
    }

    public void setRecommInfoUpdateTime(String recommInfoUpdateTime) {
        RecommInfoUpdateTime = recommInfoUpdateTime;
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

    public List<String> getSendObject() {
        return sendObject;
    }

    public void setSendObject(List<String> sendObject) {
        this.sendObject = sendObject;
    }

    public ReminderVisit getReminderVisit() {
        return reminderVisit;
    }

    public void setReminderVisit(ReminderVisit reminderVisit) {
        this.reminderVisit = reminderVisit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}