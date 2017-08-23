package com.bjike.goddess.secure.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 社保增员信息名单数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AddEmployeeDTO extends BaseDTO {
    public interface CONFIRM {
    }

    /**
     * 是否发送邮件
     */
    @NotNull(groups = {AddEmployeeDTO.CONFIRM.class}, message = "是否发送邮件不能为空")
    private Boolean send;
    /**
     * 用户名
     */
    private String[] users;

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}