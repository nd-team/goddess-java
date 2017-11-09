package com.bjike.goddess.staffmove.vo;

/**
 * 人员调动实施表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:47 ]
 * @Description: [ 人员调动实施表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffMoveImplementBVO {

    /**
     * id
     */
    private String id;
    /**
     * 被调动地区
     */
    private String mobilizedArea;

    /**
     * 被调动项目组/部门
     */
    private String mobilizedProjectGroup;

    /**
     * 调动人数
     */
    private Integer transferNum;

    /**
     * 被调动人员
     */
    private String mobilizedPeople;

    /**
     * 被调动人员主岗位
     */
    private String mobilizedMainPosition;

    /**
     * 是否需要安排住宿
     */
    private Boolean arrangeHotel;

    /**
     * 是否需要安排交通
     */
    private Boolean arrangeTraffic;

    /**
     * 计划到岗时间
     */
    private String planWorkTime;

    /**
     * 实际到岗时间
     */
    private String realityWorkTime;

    /**
     * 总经办
     */
    private String general;

    /**
     * 审核意见
     */
    private String generalIdea;

    /**
     * 是否实施调动
     */
    private Boolean obeyStaff;

    /**
     * 原决策层
     */
    private String original;

    /**
     * 审核意见
     */
    private String originalIdea;

    /**
     * 调往决策层
     */
    private String transfer;

    /**
     * 审核意见
     */
    private String transferIdea;

    /**
     * 是否解决需求
     */
    private Boolean solveDemand;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobilizedArea() {
        return mobilizedArea;
    }

    public void setMobilizedArea(String mobilizedArea) {
        this.mobilizedArea = mobilizedArea;
    }

    public String getMobilizedProjectGroup() {
        return mobilizedProjectGroup;
    }

    public void setMobilizedProjectGroup(String mobilizedProjectGroup) {
        this.mobilizedProjectGroup = mobilizedProjectGroup;
    }

    public Integer getTransferNum() {
        return transferNum;
    }

    public void setTransferNum(Integer transferNum) {
        this.transferNum = transferNum;
    }

    public String getMobilizedPeople() {
        return mobilizedPeople;
    }

    public void setMobilizedPeople(String mobilizedPeople) {
        this.mobilizedPeople = mobilizedPeople;
    }

    public String getMobilizedMainPosition() {
        return mobilizedMainPosition;
    }

    public void setMobilizedMainPosition(String mobilizedMainPosition) {
        this.mobilizedMainPosition = mobilizedMainPosition;
    }

    public Boolean getArrangeHotel() {
        return arrangeHotel;
    }

    public void setArrangeHotel(Boolean arrangeHotel) {
        this.arrangeHotel = arrangeHotel;
    }

    public Boolean getArrangeTraffic() {
        return arrangeTraffic;
    }

    public void setArrangeTraffic(Boolean arrangeTraffic) {
        this.arrangeTraffic = arrangeTraffic;
    }

    public String getPlanWorkTime() {
        return planWorkTime;
    }

    public void setPlanWorkTime(String planWorkTime) {
        this.planWorkTime = planWorkTime;
    }

    public String getRealityWorkTime() {
        return realityWorkTime;
    }

    public void setRealityWorkTime(String realityWorkTime) {
        this.realityWorkTime = realityWorkTime;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getGeneralIdea() {
        return generalIdea;
    }

    public void setGeneralIdea(String generalIdea) {
        this.generalIdea = generalIdea;
    }

    public Boolean getObeyStaff() {
        return obeyStaff;
    }

    public void setObeyStaff(Boolean obeyStaff) {
        this.obeyStaff = obeyStaff;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginalIdea() {
        return originalIdea;
    }

    public void setOriginalIdea(String originalIdea) {
        this.originalIdea = originalIdea;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getTransferIdea() {
        return transferIdea;
    }

    public void setTransferIdea(String transferIdea) {
        this.transferIdea = transferIdea;
    }

    public Boolean getSolveDemand() {
        return solveDemand;
    }

    public void setSolveDemand(Boolean solveDemand) {
        this.solveDemand = solveDemand;
    }
}