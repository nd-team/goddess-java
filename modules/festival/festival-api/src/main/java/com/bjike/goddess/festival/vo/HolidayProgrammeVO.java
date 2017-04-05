package com.bjike.goddess.festival.vo;

import com.bjike.goddess.festival.to.*;

import java.util.List;

/**
 * 法定节假日放假方案表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:03 ]
 * @Description: [ 法定节假日放假方案表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HolidayProgrammeVO {

    /**
     * id
     */
    private String id;
    /**
     * 节假日名称
     */
    private String name;

    /**
     * 放假时间安排
     */
    private String offTime;

    /**
     * 是否有加班需求
     */
    private String overTimeCondition;

    /**
     * 节假日工作安排
     */
    private String workArrange;

    /**
     * 各地区紧急联系人
     */
    private String areaRelation;

    /**
     * 节假日礼品福利
     */
    private String festivalWelfare;

    /**
     * 是否有活动需求
     */
    private String activeQuried;

    /**
     * 活动安排详情
     */
    private String activeDetail;

    /**
     * 注意事项
     */
    private String matterThings;

    /**
     * 福利模块审批意见
     */
    private String welfareAudit;

    /**
     * 运营商务部审批意见
     */
    private String operateAudit;

    /**
     * 是否通过
     */
    private String passCondition;

    /**
     * 总经办意见
     */
    private String managerAdivce;

    /**
     * 文件编号
     */
    private String fileNum;

    /**
     * 存储位置
     */
    private String storagePosition;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 公司放假时间数组
     */
    private List<CompanyFestivalTimeVO> companyFestivalTimeVOList;
    /**
     * 节假日工作安排数组
     */
    private List<HolidayWorkPlanVO> holidayWorkPlanVOList;

    /**
     * 各地区紧急联系人数组
     */
    private List<AreaRelationerVO> areaRelationerVOList;

    /**
     * 节假日福利数组
     */
    private List<WelfareVO> welfareVOList;

    /**
     * 注意事项数组
     */
    private List<NoticeThingVO> noticeThingVOList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public String getOverTimeCondition() {
        return overTimeCondition;
    }

    public void setOverTimeCondition(String overTimeCondition) {
        this.overTimeCondition = overTimeCondition;
    }

    public String getWorkArrange() {
        return workArrange;
    }

    public void setWorkArrange(String workArrange) {
        this.workArrange = workArrange;
    }

    public String getAreaRelation() {
        return areaRelation;
    }

    public void setAreaRelation(String areaRelation) {
        this.areaRelation = areaRelation;
    }

    public String getFestivalWelfare() {
        return festivalWelfare;
    }

    public void setFestivalWelfare(String festivalWelfare) {
        this.festivalWelfare = festivalWelfare;
    }

    public String getActiveQuried() {
        return activeQuried;
    }

    public void setActiveQuried(String activeQuried) {
        this.activeQuried = activeQuried;
    }

    public String getActiveDetail() {
        return activeDetail;
    }

    public void setActiveDetail(String activeDetail) {
        this.activeDetail = activeDetail;
    }

    public String getMatterThings() {
        return matterThings;
    }

    public void setMatterThings(String matterThings) {
        this.matterThings = matterThings;
    }

    public String getWelfareAudit() {
        return welfareAudit;
    }

    public void setWelfareAudit(String welfareAudit) {
        this.welfareAudit = welfareAudit;
    }

    public String getOperateAudit() {
        return operateAudit;
    }

    public void setOperateAudit(String operateAudit) {
        this.operateAudit = operateAudit;
    }

    public String getPassCondition() {
        return passCondition;
    }

    public void setPassCondition(String passCondition) {
        this.passCondition = passCondition;
    }

    public String getManagerAdivce() {
        return managerAdivce;
    }

    public void setManagerAdivce(String managerAdivce) {
        this.managerAdivce = managerAdivce;
    }

    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    public String getStoragePosition() {
        return storagePosition;
    }

    public void setStoragePosition(String storagePosition) {
        this.storagePosition = storagePosition;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<CompanyFestivalTimeVO> getCompanyFestivalTimeVOList() {
        return companyFestivalTimeVOList;
    }

    public void setCompanyFestivalTimeVOList(List<CompanyFestivalTimeVO> companyFestivalTimeVOList) {
        this.companyFestivalTimeVOList = companyFestivalTimeVOList;
    }

    public List<HolidayWorkPlanVO> getHolidayWorkPlanVOList() {
        return holidayWorkPlanVOList;
    }

    public void setHolidayWorkPlanVOList(List<HolidayWorkPlanVO> holidayWorkPlanVOList) {
        this.holidayWorkPlanVOList = holidayWorkPlanVOList;
    }

    public List<AreaRelationerVO> getAreaRelationerVOList() {
        return areaRelationerVOList;
    }

    public void setAreaRelationerVOList(List<AreaRelationerVO> areaRelationerVOList) {
        this.areaRelationerVOList = areaRelationerVOList;
    }

    public List<WelfareVO> getWelfareVOList() {
        return welfareVOList;
    }

    public void setWelfareVOList(List<WelfareVO> welfareVOList) {
        this.welfareVOList = welfareVOList;
    }

    public List<NoticeThingVO> getNoticeThingVOList() {
        return noticeThingVOList;
    }

    public void setNoticeThingVOList(List<NoticeThingVO> noticeThingVOList) {
        this.noticeThingVOList = noticeThingVOList;
    }
}