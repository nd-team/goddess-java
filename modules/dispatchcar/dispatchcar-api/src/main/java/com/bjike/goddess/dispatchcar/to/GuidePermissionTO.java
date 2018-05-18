package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dispatchcar.enums.GuideAddStatus;
import com.bjike.goddess.dispatchcar.enums.GuideAddrStatus;

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

    public interface TestAdd {
    }

    /**
     * 导航栏类型
     */
    @NotNull(groups = {GuidePermissionTO.TestAdd.class}, message = "导航栏类型不能为空")
    private GuideAddStatus guideAddStatus;

    public GuideAddStatus getGuideAddStatus() {
        return guideAddStatus;
    }

    public void setGuideAddStatus(GuideAddStatus guideAddStatus) {
        this.guideAddStatus = guideAddStatus;
    }
}

