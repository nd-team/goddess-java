package com.bjike.goddess.employeecontract.to;

import com.bjike.goddess.employeecontract.enums.GuideAddrStatus;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
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
