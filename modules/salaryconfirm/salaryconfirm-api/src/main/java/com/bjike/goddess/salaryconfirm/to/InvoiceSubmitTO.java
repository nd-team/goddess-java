package com.bjike.goddess.salaryconfirm.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 上交发票
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-17 05:47 ]
 * @Description: [ 上交发票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InvoiceSubmitTO extends BaseTO {

    /**
     * 上交日期
     */
    @NotBlank(message = "上交日期不能为空",groups = {ADD.class, EDIT.class})
    private String submitDate;

    /**
     * 发票金额
     */
    @NotNull(message = "上交金额不能为空",groups = {ADD.class, EDIT.class})
    private Double amount;

    /**
     * 上交人
     */
    @NotBlank(message = "上交人不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空",groups = {ADD.class, EDIT.class})
    private String employeeNumber;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String modifyUser;


    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}