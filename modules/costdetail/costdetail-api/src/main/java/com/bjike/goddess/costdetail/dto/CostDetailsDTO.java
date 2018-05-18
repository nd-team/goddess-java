package com.bjike.goddess.costdetail.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.user.entity.rbac.Group;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 成本明细数据传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CostDetailsDTO extends BaseDTO {
    public interface testSee{}
    /**
     * 日期
     */
    @NotBlank(groups = {testSee.class}, message = "日期不能为空")
    private String costTime;
    /**
     * 部门
     */
    @NotBlank(groups = {testSee.class}, message = "部门不能为空")
    private String department;

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}