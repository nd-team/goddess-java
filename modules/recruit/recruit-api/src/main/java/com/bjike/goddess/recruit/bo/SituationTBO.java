package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 招聘情况统计业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-20 08:26 ]
 * @Description: [ 招聘情况统计业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SituationTBO extends BaseBO {

    /**
     * 日期
     */
    private String date;

    /**
     * 招聘地区
     */
    private String recruitArea;

    /**
     * 招聘部门/项目组
     */
    private String recruitDepart;

    /**
     * 招聘岗位
     */
    private String recruitPosition;

    /**
     * 未邀约成功量
     */
    private Integer failInterNum;

    /**
     * 未邀约成功原因类型
     */
    private String failInterReason;

    /**
     * 未邀约成功原因类型出现量
     */
    private Integer failInterReasonNum;

    /**
     * 未应约初试量
     */
    private Integer denyFirViewNum;

    /**
     * 未应约初试原因类型
     */
    private String denyFirViewReason;

    /**
     * 未应约初试原因类型出现量
     */
    private Integer denyFirViewReasonNum;

    /**
     * 未接受录取量
     */
    private Integer denyAdmitNum;

    /**
     * 未接受录取原因类型
     */
    private String denyAdmitReason;

    /**
     * 未接受录取原因类型出现量
     */
    private Integer denyAdmitReasonNum;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecruitArea() {
        return recruitArea;
    }

    public void setRecruitArea(String recruitArea) {
        this.recruitArea = recruitArea;
    }

    public String getRecruitDepart() {
        return recruitDepart;
    }

    public void setRecruitDepart(String recruitDepart) {
        this.recruitDepart = recruitDepart;
    }

    public String getRecruitPosition() {
        return recruitPosition;
    }

    public void setRecruitPosition(String recruitPosition) {
        this.recruitPosition = recruitPosition;
    }

    public Integer getFailInterNum() {
        return failInterNum;
    }

    public void setFailInterNum(Integer failInterNum) {
        this.failInterNum = failInterNum;
    }

    public String getFailInterReason() {
        return failInterReason;
    }

    public void setFailInterReason(String failInterReason) {
        this.failInterReason = failInterReason;
    }

    public Integer getFailInterReasonNum() {
        return failInterReasonNum;
    }

    public void setFailInterReasonNum(Integer failInterReasonNum) {
        this.failInterReasonNum = failInterReasonNum;
    }

    public Integer getDenyFirViewNum() {
        return denyFirViewNum;
    }

    public void setDenyFirViewNum(Integer denyFirViewNum) {
        this.denyFirViewNum = denyFirViewNum;
    }

    public String getDenyFirViewReason() {
        return denyFirViewReason;
    }

    public void setDenyFirViewReason(String denyFirViewReason) {
        this.denyFirViewReason = denyFirViewReason;
    }

    public Integer getDenyFirViewReasonNum() {
        return denyFirViewReasonNum;
    }

    public void setDenyFirViewReasonNum(Integer denyFirViewReasonNum) {
        this.denyFirViewReasonNum = denyFirViewReasonNum;
    }

    public Integer getDenyAdmitNum() {
        return denyAdmitNum;
    }

    public void setDenyAdmitNum(Integer denyAdmitNum) {
        this.denyAdmitNum = denyAdmitNum;
    }

    public String getDenyAdmitReason() {
        return denyAdmitReason;
    }

    public void setDenyAdmitReason(String denyAdmitReason) {
        this.denyAdmitReason = denyAdmitReason;
    }

    public Integer getDenyAdmitReasonNum() {
        return denyAdmitReasonNum;
    }

    public void setDenyAdmitReasonNum(Integer denyAdmitReasonNum) {
        this.denyAdmitReasonNum = denyAdmitReasonNum;
    }
}