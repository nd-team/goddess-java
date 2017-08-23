package com.bjike.goddess.businesscommission.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 业务提成定额表
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:13:56.348 ]
 * @Description: [ 业务提成定额表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommissionQuotaDeleteFileTO extends BaseTO {

    public interface TestDEL{}

    /**
     * 删除路径
     */
    @NotNull(groups = {CommissionQuotaDeleteFileTO.TestDEL.class},message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}