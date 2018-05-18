package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 社保卡补办登记表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 06:09 ]
 * @Description: [ 社保卡补办登记表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReplaceRegisterTO extends BaseTO {
    public interface resources{}
    public interface finance{}
    public interface general{}

    /**
     * 补办时间
     */
    @NotBlank(message = "补办时间不能为空",groups = {ADD.class, EDIT.class})
    private String replaceTime;

    /**
     * 持卡人姓名
     */
    @NotBlank(message = "持卡人姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 身份证号码
     */
    @NotNull(message = "身份证号码不能为空",groups = {ADD.class, EDIT.class})
    private Integer card;

    /**
     * 原社保账户号码
     */
    @NotNull(message = "原社保账户号码不能为空",groups = {ADD.class, EDIT.class})
    private Integer oldSecure;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 补办机构
     */
    @NotBlank(message = "补办机构不能为空",groups = {ADD.class, EDIT.class})
    private String replaceMechanism;

    /**
     * 领用时间
     */
    @NotBlank(message = "领用时间不能为空",groups = {ADD.class, EDIT.class})
    private String drawTime;

    /**
     * 补办原因
     */
    @NotBlank(message = "补办原因不能为空",groups = {ADD.class, EDIT.class})
    private String cause;

    /**
     * 补办人
     */
    @NotBlank(message = "补办人不能为空",groups = {ADD.class, EDIT.class})
    private String replaceName;

    /**
     * 综合资源部意见
     */
    @NotBlank(message = "综合资源部意见不能为空",groups = {ReplaceRegisterTO.resources.class})
    private String resourcesOpinion;

    /**
     * 运营财务部意见
     */
    @NotBlank(message = "运营财务部意见不能为空",groups = {ReplaceRegisterTO.finance.class})
    private String financeOpinion;

    /**
     * 总经办意见
     */
    @NotBlank(message = "总经办意见不能为空",groups = {ReplaceRegisterTO.general.class})
    private String generalOpinion;

    /**
     * 预挂失有效时限
     */
    @NotNull(message = "预挂失有效时限不能为空",groups = {ADD.class, EDIT.class})
    private Integer lossTime;

    /**
     * 补办人是否领取新卡
     */
    @NotNull(message = "补办人是否领取新卡不能为空",groups = {ADD.class, EDIT.class})
    private Boolean newCard;

    /**
     * 现社保账户号码
     */
    @NotNull(message = "现社保账户号码不能为空",groups = {ADD.class, EDIT.class})
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