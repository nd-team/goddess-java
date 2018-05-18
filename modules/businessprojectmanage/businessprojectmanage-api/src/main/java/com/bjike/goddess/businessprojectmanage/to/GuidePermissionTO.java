package com.bjike.goddess.businessprojectmanage.to;

import com.bjike.goddess.businessprojectmanage.enums.GuideAddrStatus;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 导航栏权限
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 04:24 ]
 * @Description: [ 导航栏权限 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GuidePermissionTO extends BaseTO {

    public interface TestAdd{}
    /**
     * 导航栏类型
     */
    @NotNull(groups = {GuidePermissionTO.TestAdd.class} , message = "导航栏类型不能为空")
    private GuideAddrStatus guideAddrStatus;


    public GuideAddrStatus getGuideAddrStatus() {
        return guideAddrStatus;
    }

    public void setGuideAddrStatus(GuideAddrStatus guideAddrStatus) {
        this.guideAddrStatus = guideAddrStatus;
    }
}