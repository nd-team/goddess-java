package com.bjike.goddess.secure.vo;

/**
 * 社保卡领取登记表表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 05:55 ]
 * @Description: [ 社保卡领取登记表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DrawRegisterVO {

    /**
     * id
     */
    private String id;
    /**
     * 领取时间
     */
    private String drawTime;

    /**
     * 持卡人姓名
     */
    private String name;

    /**
     * 地区
     */
    private String area;

    /**
     * 领取机构
     */
    private String mechanism;

    /**
     * 是否可从领取机构中领取社保卡
     */
    private Boolean drawSecureCard;

    /**
     * 领取内容
     */
    private String content;

    /**
     * 领取数量
     */
    private Integer number;

    /**
     * 领取人姓名
     */
    private String drawName;

    /**
     * 模块负责人审批意见
     */
    private String opinion;

    /**
     * 领取完成
     */
    private String drawFinish;

    /**
     * 备注
     */
    private String remark;

    /**
     * 持卡人是否领取
     */
    private Boolean draw;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public Boolean getDrawSecureCard() {
        return drawSecureCard;
    }

    public void setDrawSecureCard(Boolean drawSecureCard) {
        this.drawSecureCard = drawSecureCard;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDrawName() {
        return drawName;
    }

    public void setDrawName(String drawName) {
        this.drawName = drawName;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getDrawFinish() {
        return drawFinish;
    }

    public void setDrawFinish(String drawFinish) {
        this.drawFinish = drawFinish;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDraw() {
        return draw;
    }

    public void setDraw(Boolean draw) {
        this.draw = draw;
    }
}