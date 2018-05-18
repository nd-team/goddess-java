package com.bjike.goddess.staffwelfare.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 个人节日
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 01:56 ]
 * @Description: [ 个人节日 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonalFestivalTO extends BaseTO {

    /**
     * 节日名称
     */
    @NotBlank(message = "节日名称不能为空!", groups = {ADD.class, EDIT.class})
    private String festivalName;

    /**
     * 节日时间
     */
    @NotBlank(message = "节日时间不能为空!", groups = {ADD.class, EDIT.class})
    private String festivalDate;

    /**
     * 可见人员
     */
    @NotNull(message = "可见人员不能为空!", groups = {ADD.class, EDIT.class})
    private String[] visibleUsers;

    /**
     * 提醒时间
     */
    @NotBlank(message = "提醒时间不能为空!", groups = {ADD.class, EDIT.class})
    private String remindTime;

    /**
     * 提示语
     */
    @NotBlank(message = "运营商务部意见不能为空!", groups = {ADD.class, EDIT.class})
    private String remindInfo;

    /**
     * 是否开通一声祝福
     */
    @NotNull(message = "是否开通一声祝福不能为空!", groups = {ADD.class, EDIT.class})
    private Boolean openWish;

    /**
     * 答谢语
     */
    @NotBlank(message = "答谢语不能为空!", groups = {ADD.class, EDIT.class})
    private String thankStatement;

    /**
     * 备注
     */
    private String remark;
    /**
     * 节日人姓名
     */
    private String userName;

    /**
     * 节日人id
     */
    private String userId;


    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getFestivalDate() {
        return festivalDate;
    }

    public void setFestivalDate(String festivalDate) {
        this.festivalDate = festivalDate;
    }

    public String[] getVisibleUsers() {
        return visibleUsers;
    }

    public void setVisibleUsers(String[] visibleUsers) {
        this.visibleUsers = visibleUsers;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemindInfo() {
        return remindInfo;
    }

    public void setRemindInfo(String remindInfo) {
        this.remindInfo = remindInfo;
    }

    public Boolean getOpenWish() {
        return openWish;
    }

    public void setOpenWish(Boolean openWish) {
        this.openWish = openWish;
    }

    public String getThankStatement() {
        return thankStatement;
    }

    public void setThankStatement(String thankStatement) {
        this.thankStatement = thankStatement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}