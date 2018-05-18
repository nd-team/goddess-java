package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 简介管理汇总
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-04-06 10:15 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummationBO extends BaseBO {

    /**
     * 可用于招投标的数量
     */
    private Integer availBiddingNum;

    /**
     * 招投标类型简介数量
     */
    private Integer biddingNum;

    /**
     * 入职培训所用简介数量
     */
    private Integer orientationNum;

    /**
     * 公司官网所用简介数量
     */
    private Integer officialWebsiteNum;

    /**
     * 招聘所用简介数量
     */
    private Integer recruitNum;

    /**
     * 入职人数
     */
    private Integer inductionNum;

    /**
     * 已有个人简介数(在职)
     */
    private Integer existingProfileNum;

    /**
     * 离职人数
     */
    private Integer leaveOffice;

    /**
     * 已冻结有个人简介数
     */
    private Integer hasFrozen;

    public Integer getAvailBiddingNum() {
        return availBiddingNum;
    }

    public void setAvailBiddingNum(Integer availBiddingNum) {
        this.availBiddingNum = availBiddingNum;
    }

    public Integer getBiddingNum() {
        return biddingNum;
    }

    public void setBiddingNum(Integer biddingNum) {
        this.biddingNum = biddingNum;
    }

    public Integer getOrientationNum() {
        return orientationNum;
    }

    public void setOrientationNum(Integer orientationNum) {
        this.orientationNum = orientationNum;
    }

    public Integer getOfficialWebsiteNum() {
        return officialWebsiteNum;
    }

    public void setOfficialWebsiteNum(Integer officialWebsiteNum) {
        this.officialWebsiteNum = officialWebsiteNum;
    }

    public Integer getRecruitNum() {
        return recruitNum;
    }

    public void setRecruitNum(Integer recruitNum) {
        this.recruitNum = recruitNum;
    }

    public Integer getInductionNum() {
        return inductionNum;
    }

    public void setInductionNum(Integer inductionNum) {
        this.inductionNum = inductionNum;
    }

    public Integer getExistingProfileNum() {
        return existingProfileNum;
    }

    public void setExistingProfileNum(Integer existingProfileNum) {
        this.existingProfileNum = existingProfileNum;
    }

    public Integer getLeaveOffice() {
        return leaveOffice;
    }

    public void setLeaveOffice(Integer leaveOffice) {
        this.leaveOffice = leaveOffice;
    }

    public Integer getHasFrozen() {
        return hasFrozen;
    }

    public void setHasFrozen(Integer hasFrozen) {
        this.hasFrozen = hasFrozen;
    }
}