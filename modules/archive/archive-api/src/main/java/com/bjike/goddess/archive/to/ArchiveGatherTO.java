package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 档案收集
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:22 ]
 * @Description: [ 档案收集 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ArchiveGatherTO extends BaseTO {

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 自愿放弃购买社保协议书
     */
    @NotNull(message = "自愿放弃购买社保协议书不能为空",groups = {ADD.class, EDIT.class})
    private Boolean abandon;

    /**
     * 转正定级档案收集
     */
    @NotNull(message = "转正定级档案收集不能为空",groups = {ADD.class, EDIT.class})
    private Boolean become;

    /**
     * 调动或任命
     */
    private String transfer;

    /**
     * 岗位晋升
     */
    private String promotion;

    /**
     * 技能评定
     */
    private String evaluate;

    /**
     * 特批补助
     */
    private String subsidy;

    /**
     * 技能证书
     */
    private String skill;

    /**
     * 其他档案
     */
    private String other;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAbandon() {
        return abandon;
    }

    public void setAbandon(Boolean abandon) {
        this.abandon = abandon;
    }

    public Boolean getBecome() {
        return become;
    }

    public void setBecome(Boolean become) {
        this.become = become;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}