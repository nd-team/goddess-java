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
public class DimissionInfoAddEditTO extends BaseTO {

    /**
     * 私人邮箱
     */
    @NotBlank(message = "私人邮箱不能为空", groups = {ADD.class, EDIT.class})
    private String email;

    /**
     * 离职原因
     */
    @NotBlank(message = "离职原因不能为空", groups = {ADD.class, EDIT.class})
    private String reason;

    /**
     * 申请离职日期
     */
    @NotBlank(message = "申请离职日期不能为空", groups = {ADD.class, EDIT.class})
    private String applyDate;

    /**
     * 是否申请提前离职
     */
    @NotNull(message = "是否申请提前离职不能为空", groups = {ADD.class, EDIT.class})
    private Boolean advanceApply;

    /**
     * 提前离职日期
     */
    private String advanceDate;

    /**
     * 提前离职原因
     */
    private String advanceReason;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Boolean getAdvanceApply() {
        return advanceApply;
    }

    public void setAdvanceApply(Boolean advanceApply) {
        this.advanceApply = advanceApply;
    }

    public String getAdvanceDate() {
        return advanceDate;
    }

    public void setAdvanceDate(String advanceDate) {
        this.advanceDate = advanceDate;
    }

    public String getAdvanceReason() {
        return advanceReason;
    }

    public void setAdvanceReason(String advanceReason) {
        this.advanceReason = advanceReason;
    }
}