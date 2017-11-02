package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-09 17:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecruitWayBO extends BaseBO {

    /**
     * 招聘渠道
     */
    private String recruitName;

    /**
     * 会员名
     */
    private String vipName;

    /**
     * 会员密码
     */
    private String vipPassword;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 渠道联系人
     */
    private String channelContact;

    /**
     * 渠道联系人的联系方式
     */
    private String channelContactPhone;

    /**
     * 公司负责员工
     */
    private String firmPrincipal;

    /**
     * 使用的套餐和费用
     */
    private String comboAndCharge;

    /**
     * 使用情况
     */
    private String situation;

    /**
     * 效果评价
     */
    private String effect;

    /**
     * 备注
     */
    private String remarks;
    /**
     * 渠道开发时间
     */
    private String channelTime;
    /**
     * 收费情况
     */
    private String chargeCase;
    /**
     * 剩余职位发布数
     */
    private Integer postNum;
    /**
     * 剩余简历下载数
     */
    private Integer resumeNum;
    /**
     * 适用招聘岗位
     */
    private String suitPosition;
    /**
     * 状态
     */
    private Status status;

    public String getChannelTime() {
        return channelTime;
    }

    public void setChannelTime(String channelTime) {
        this.channelTime = channelTime;
    }

    public String getChargeCase() {
        return chargeCase;
    }

    public void setChargeCase(String chargeCase) {
        this.chargeCase = chargeCase;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Integer getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(Integer resumeNum) {
        this.resumeNum = resumeNum;
    }

    public String getSuitPosition() {
        return suitPosition;
    }

    public void setSuitPosition(String suitPosition) {
        this.suitPosition = suitPosition;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRecruitName() {
        return recruitName;
    }

    public void setRecruitName(String recruitName) {
        this.recruitName = recruitName;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getVipPassword() {
        return vipPassword;
    }

    public void setVipPassword(String vipPassword) {
        this.vipPassword = vipPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChannelContact() {
        return channelContact;
    }

    public void setChannelContact(String channelContact) {
        this.channelContact = channelContact;
    }

    public String getChannelContactPhone() {
        return channelContactPhone;
    }

    public void setChannelContactPhone(String channelContactPhone) {
        this.channelContactPhone = channelContactPhone;
    }

    public String getFirmPrincipal() {
        return firmPrincipal;
    }

    public void setFirmPrincipal(String firmPrincipal) {
        this.firmPrincipal = firmPrincipal;
    }

    public String getComboAndCharge() {
        return comboAndCharge;
    }

    public void setComboAndCharge(String comboAndCharge) {
        this.comboAndCharge = comboAndCharge;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
