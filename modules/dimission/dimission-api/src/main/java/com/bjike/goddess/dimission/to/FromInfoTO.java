package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dimission.enums.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 离职信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:12 ]
 * @Description: [ 离职信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FromInfoTO extends BaseTO {

    /**
     * 离职人员姓名
     */
    @NotBlank(message = "离职人员姓名", groups = {ADD.class, EDIT.class})
    private String username;


    /**
     * 私人邮箱
     */
    @NotBlank(message = "私人邮箱姓名", groups = {ADD.class, EDIT.class})
    private String email;
    /**
     * 离职原因
     */
    @NotBlank(message = "私人邮箱不能为空", groups = {ADD.class, EDIT.class})
    private String reason;

    /**
     * 申请离职日期
     */
    private String applyDate;

    /**
     * 正常离职时间
     */
    @NotBlank(message = "正常离职时间不能为空", groups = {ADD.class, EDIT.class})
    private String dimissionDate;

    /**
     * 离职面谈负责人
     */
    private String liable;


    /**
     * 面谈内容
     */
    private String content;

    /**
     * 面谈意见
     */
    private String liableOpinion;

    /**
     * 离职办理状态
     */
    @NotNull(message = "离职办理状态不能为空", groups = {ADD.class, EDIT.class})
    private HandleStatus handle;

    /**
     * 离职确认情况
     */
    @NotNull(message = "离职确认情况不能为空", groups = {ADD.class, EDIT.class})
    private ConfirmationType dimissionConfirmation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(String dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public String getLiable() {
        return liable;
    }

    public void setLiable(String liable) {
        this.liable = liable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLiableOpinion() {
        return liableOpinion;
    }

    public void setLiableOpinion(String liableOpinion) {
        this.liableOpinion = liableOpinion;
    }

    public HandleStatus getHandle() {
        return handle;
    }

    public void setHandle(HandleStatus handle) {
        this.handle = handle;
    }

    public ConfirmationType getDimissionConfirmation() {
        return dimissionConfirmation;
    }

    public void setDimissionConfirmation(ConfirmationType dimissionConfirmation) {
        this.dimissionConfirmation = dimissionConfirmation;
    }
}