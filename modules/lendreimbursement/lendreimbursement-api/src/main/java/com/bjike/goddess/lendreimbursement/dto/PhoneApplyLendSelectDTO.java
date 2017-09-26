package com.bjike.goddess.lendreimbursement.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.lendreimbursement.enums.LendPhoneSelectStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 申请借款下拉框数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款下拉框数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PhoneApplyLendSelectDTO extends BaseDTO {

    public interface TESTDepart{}
    public interface TESTPNAME{}

    /**
     * 地区
     */
    @NotBlank(groups = {PhoneApplyLendSelectDTO.TESTDepart.class,PhoneApplyLendSelectDTO.TESTPNAME.class},message = "地区不能为空")
    private String area;

    /**
     * 部门项目组
     */
    @NotBlank(groups = {PhoneApplyLendSelectDTO.TESTPNAME.class},message = "部门不能为空")
    private String projectGroup;



    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }
}