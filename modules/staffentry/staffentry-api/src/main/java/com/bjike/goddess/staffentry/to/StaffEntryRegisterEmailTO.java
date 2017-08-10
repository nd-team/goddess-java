package com.bjike.goddess.staffentry.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 员工入职注册
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffEntryRegisterEmailTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 列表id
     */
    @NotBlank(groups = {StaffEntryRegisterEmailTO.TestAdd.class} , message = "列表id不能为空")
    private String empId ;

    /**
     * 用户入职注册个人邮箱
     */
    @NotBlank(groups = {StaffEntryRegisterEmailTO.TestAdd.class} , message = "用户入职注册个人邮箱不能为空")
    private String emailAccount;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }
}