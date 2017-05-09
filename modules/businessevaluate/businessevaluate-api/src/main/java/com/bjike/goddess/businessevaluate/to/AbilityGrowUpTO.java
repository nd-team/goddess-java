package com.bjike.goddess.businessevaluate.to;

import com.bjike.goddess.businessevaluate.enums.GroupUpType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 能力成长
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 04:13 ]
 * @Description: [ 能力成长 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AbilityGrowUpTO extends BaseTO {

    /**
     * 能力成长类型
     */
    @NotNull(message = "能力成长类型不能为空",groups = {ADD.class, EDIT.class})
    private GroupUpType groupUpType;

    /**
     * 一月
     */
    @NotNull(message = "一月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double januaryMoney;

    /**
     * 二月
     */
    @NotNull(message = "二月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double februaryMoney;

    /**
     * 三月
     */
    @NotNull(message = "三月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double marchMoney;

    /**
     * 四月
     */
    @NotNull(message = "四月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double aprilMoney;

    /**
     * 五月
     */
    @NotNull(message = "五月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double mayMoney;

    /**
     * 六月
     */
    @NotNull(message = "六月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double juneMoney;

    /**
     * 七月
     */
    @NotNull(message = "七月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double julyMoney;

    /**
     * 八月
     */
    @NotNull(message = "八月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double augustMoney;

    /**
     * 九月
     */
    @NotNull(message = "九月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double septemberMoney;

    /**
     * 十月
     */
    @NotNull(message = "十月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double octoberMoney;

    /**
     * 十一月
     */
    @NotNull(message = "十一月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double novemberMoney;

    /**
     * 十二月
     */
    @NotNull(message = "十二月金额不能为空",groups = {ADD.class, EDIT.class})
    private Double decemberMoney;

    /**
     * 项目信息Id
     */
    @NotBlank(message = "项目不能为空",groups = {ADD.class, EDIT.class})
    private String projectInfoId;


    public GroupUpType getGroupUpType() {
        return groupUpType;
    }

    public void setGroupUpType(GroupUpType groupUpType) {
        this.groupUpType = groupUpType;
    }

    public Double getJanuaryMoney() {
        return januaryMoney;
    }

    public void setJanuaryMoney(Double januaryMoney) {
        this.januaryMoney = januaryMoney;
    }

    public Double getFebruaryMoney() {
        return februaryMoney;
    }

    public void setFebruaryMoney(Double februaryMoney) {
        this.februaryMoney = februaryMoney;
    }

    public Double getMarchMoney() {
        return marchMoney;
    }

    public void setMarchMoney(Double marchMoney) {
        this.marchMoney = marchMoney;
    }

    public Double getAprilMoney() {
        return aprilMoney;
    }

    public void setAprilMoney(Double aprilMoney) {
        this.aprilMoney = aprilMoney;
    }

    public Double getMayMoney() {
        return mayMoney;
    }

    public void setMayMoney(Double mayMoney) {
        this.mayMoney = mayMoney;
    }

    public Double getJuneMoney() {
        return juneMoney;
    }

    public void setJuneMoney(Double juneMoney) {
        this.juneMoney = juneMoney;
    }

    public Double getJulyMoney() {
        return julyMoney;
    }

    public void setJulyMoney(Double julyMoney) {
        this.julyMoney = julyMoney;
    }

    public Double getAugustMoney() {
        return augustMoney;
    }

    public void setAugustMoney(Double augustMoney) {
        this.augustMoney = augustMoney;
    }

    public Double getSeptemberMoney() {
        return septemberMoney;
    }

    public void setSeptemberMoney(Double septemberMoney) {
        this.septemberMoney = septemberMoney;
    }

    public Double getOctoberMoney() {
        return octoberMoney;
    }

    public void setOctoberMoney(Double octoberMoney) {
        this.octoberMoney = octoberMoney;
    }

    public Double getNovemberMoney() {
        return novemberMoney;
    }

    public void setNovemberMoney(Double novemberMoney) {
        this.novemberMoney = novemberMoney;
    }

    public Double getDecemberMoney() {
        return decemberMoney;
    }

    public void setDecemberMoney(Double decemberMoney) {
        this.decemberMoney = decemberMoney;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}