package com.bjike.goddess.contacts.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 公共邮箱管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommonalityTO extends BaseTO {

    /**
     * 项目组/部门ID
     */
    @NotNull(message = "项目组/部门ID不能为空", groups = {ADD.class, EDIT.class})
    private String departmentId;

    /**
     * 邮箱地址
     */
    @NotNull(message = "邮箱地址不能为空", groups = {ADD.class, EDIT.class})
    private String email;


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}