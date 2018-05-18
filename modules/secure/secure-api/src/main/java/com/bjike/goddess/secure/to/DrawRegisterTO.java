package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.secure.entity.DrawRegister;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 社保卡领取登记表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 05:55 ]
 * @Description: [ 社保卡领取登记表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DrawRegisterTO extends BaseTO {
    public interface charge{}

    /**
     * 领取时间
     */
    @NotBlank(message = "领用时间不能为空",groups = {ADD.class, EDIT.class})
    private String drawTime;

    /**
     * 持卡人姓名
     */
    @NotBlank(message = "持卡人姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 领取机构
     */
    @NotBlank(message = "领取机构不能为空",groups = {ADD.class, EDIT.class})
    private String mechanism;

    /**
     * 是否可从领取机构中领取社保卡
     */
    @NotNull(message = "是否可从领取机构中领取社保卡不能为空",groups = {ADD.class, EDIT.class})
    private Boolean drawSecureCard;

    /**
     * 领取内容
     */
    @NotBlank(message = "领取内容不能为空",groups = {ADD.class, EDIT.class})
    private String content;

    /**
     * 领取数量
     */
    @NotNull(message = "领取数量不能为空",groups = {ADD.class, EDIT.class})
    private Integer number;

    /**
     * 领取人姓名
     */
    @NotBlank(message = "领取人姓名不能为空",groups = {ADD.class, EDIT.class})
    private String drawName;

    /**
     * 模块负责人审批意见
     */
    @NotBlank(message = "模块负责人审批意见不能为空",groups = {DrawRegisterTO.charge.class})
    private String opinion;

    /**
     * 领取完成
     */
    @NotBlank(message = "领取完成不能为空",groups = {ADD.class, EDIT.class})
    private String drawFinish;

    /**
     * 备注
     */
    private String remark;

    /**
     * 持卡人是否领取
     */
    @NotNull(message = "持卡人是否领取不能为空",groups = {ADD.class, EDIT.class})
    private Boolean draw;


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