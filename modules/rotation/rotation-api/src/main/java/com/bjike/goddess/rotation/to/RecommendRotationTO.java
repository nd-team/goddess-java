package com.bjike.goddess.rotation.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 岗位轮换推荐
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:28 ]
 * @Description: [ 岗位轮换推荐 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendRotationTO extends BaseTO {

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class})
    private String username;

    /**
     * 举荐轮换等级
     */
    @NotBlank(message = "举荐轮换等级不能为空", groups = {ADD.class})
    private String applyLevelId;

    /**
     * 举荐原因
     */
    @NotBlank(message = "举荐原因不能为空", groups = {ADD.class})
    private String reason;

    /**
     * 轮换后岗位等级
     */
    @NotBlank(message = "轮换后岗位等级不能为空", groups = {EDIT.class})
    private String rotationLevelId;

    /**
     * 总经办
     */
    private String general;

    /**
     * 总经办意见
     */
    @NotBlank(message = "总经办意见不能为空", groups = {EDIT.class})
    private String opinion;

    /**
     * 是否通过
     */
    @NotNull(message = "是否通过不能为空", groups = {EDIT.class})
    private Boolean pass;

    /**
     * 轮换时间
     */
    @NotBlank(message = "轮换时间不能为空", groups = {EDIT.class})
    private String rotationDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplyLevelId() {
        return applyLevelId;
    }

    public void setApplyLevelId(String applyLevelId) {
        this.applyLevelId = applyLevelId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRotationLevelId() {
        return rotationLevelId;
    }

    public void setRotationLevelId(String rotationLevelId) {
        this.rotationLevelId = rotationLevelId;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getRotationDate() {
        return rotationDate;
    }

    public void setRotationDate(String rotationDate) {
        this.rotationDate = rotationDate;
    }
}