package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 简洁交流讨论纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 简洁交流讨论纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultiwheelSummaryTO extends BaseTO {

    /**
     * 实际会议时间
     */
    @NotBlank(message = "实际会议时间不能为空",groups = {EDIT.class})
    private String actualTime;

    /**
     * 会议地点
     */
    @NotBlank(message = "会议地点不能为空",groups = {EDIT.class})
    private String place;

    /**
     * 实际参会人员
     */
    @NotBlank(message = "实际参会人员不能为空",groups = {EDIT.class})
    private String actualUsers;

    /**
     * 会议主持人
     */
    @NotBlank(message = "会议主持人不能为空",groups = {EDIT.class})
    private String compere;

    /**
     * 新增参会人员
     */
    private String addUsers;

    /**
     * 未参会人员
     */
    private String notAttendUsers;

    /**
     * 参会人数
     */
    @NotNull(message = "参会人数不能为空",groups = {EDIT.class})
    private Integer attendAccount;

    /**
     * 会议所属模块
     */
    @NotBlank(message = "会议所属模块不能为空",groups = {EDIT.class})
    private String module;

    /**
     * 会议所属部门
     */
    @NotBlank(message = "会议所属部门不能为空",groups = {EDIT.class})
    private String department;

    /**
     * 备注
     */
    private String remark;


    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getActualUsers() {
        return actualUsers;
    }

    public void setActualUsers(String actualUsers) {
        this.actualUsers = actualUsers;
    }

    public String getAddUsers() {
        return addUsers;
    }

    public void setAddUsers(String addUsers) {
        this.addUsers = addUsers;
    }

    public String getNotAttendUsers() {
        return notAttendUsers;
    }

    public void setNotAttendUsers(String notAttendUsers) {
        this.notAttendUsers = notAttendUsers;
    }

    public Integer getAttendAccount() {
        return attendAccount;
    }

    public void setAttendAccount(Integer attendAccount) {
        this.attendAccount = attendAccount;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompere() {
        return compere;
    }

    public void setCompere(String compere) {
        this.compere = compere;
    }
}