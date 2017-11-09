package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.AuditType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 审批时间设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:37 ]
 * @Description: [ 审批时间设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AuditTimeSetTO extends BaseTO {

    /**
     * 项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "项目组不能为空")
    private String depart;

    /**
     * 岗位
     */
    @NotBlank(groups = {ADD.class, EDIT.class},message = "岗位不能为空")
    private String position;

    /**
     * 审批类型
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "审批类型不能为空")
    private AuditType type;

    /**
     * 设置审核时间（小时内）
     */
    @NotNull(groups = {ADD.class, EDIT.class},message = "设置审核时间（小时内）不能为空")
    private Integer time;

    /**
     * 备注
     */
    private String remark;

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public AuditType getType() {
        return type;
    }

    public void setType(AuditType type) {
        this.type = type;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}