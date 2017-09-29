package com.bjike.goddess.secure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 社保卡补办登记表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 06:09 ]
 * @Description: [ 社保卡补办登记表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReplaceRegisterBO extends BaseBO {

    /**
     * 补办时间
     */
    private String replaceTime;

    /**
     * 持卡人姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private Integer card;

    /**
     * 原社保账户号码
     */
    private Integer oldSecure;

    /**
     * 地区
     */
    private String area;

    /**
     * 补办机构
     */
    private String replaceMechanism;

    /**
     * 领用时间
     */
    private String drawTime;

    /**
     * 补办原因
     */
    private String cause;

    /**
     * 补办人
     */
    private String replaceName;

    /**
     * 综合资源部意见
     */
    private String resourcesOpinion;

    /**
     * 运营财务部意见
     */
    private String financeOpinion;

    /**
     * 总经办意见
     */
    private String generalOpinion;

    /**
     * 预挂失有效时限
     */
    private Integer lossTime;

    /**
     * 补办人是否领取新卡
     */
    private Boolean newCard;

    /**
     * 现社保账户号码
     */
    private Integer newSecure;

    /**
     * 备注
     */
    private String remark;


    public String getReplaceTime() {
        return replaceTime;
    }

    public void setReplaceTime(String replaceTime) {
        this.replaceTime = replaceTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public Integer getOldSecure() {
        return oldSecure;
    }

    public void setOldSecure(Integer oldSecure) {
        this.oldSecure = oldSecure;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getReplaceMechanism() {
        return replaceMechanism;
    }

    public void setReplaceMechanism(String replaceMechanism) {
        this.replaceMechanism = replaceMechanism;
    }

    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }

    public String getResourcesOpinion() {
        return resourcesOpinion;
    }

    public void setResourcesOpinion(String resourcesOpinion) {
        this.resourcesOpinion = resourcesOpinion;
    }

    public String getFinanceOpinion() {
        return financeOpinion;
    }

    public void setFinanceOpinion(String financeOpinion) {
        this.financeOpinion = financeOpinion;
    }

    public String getGeneralOpinion() {
        return generalOpinion;
    }

    public void setGeneralOpinion(String generalOpinion) {
        this.generalOpinion = generalOpinion;
    }

    public Integer getLossTime() {
        return lossTime;
    }

    public void setLossTime(Integer lossTime) {
        this.lossTime = lossTime;
    }

    public Boolean getNewCard() {
        return newCard;
    }

    public void setNewCard(Boolean newCard) {
        this.newCard = newCard;
    }

    public Integer getNewSecure() {
        return newSecure;
    }

    public void setNewSecure(Integer newSecure) {
        this.newSecure = newSecure;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}