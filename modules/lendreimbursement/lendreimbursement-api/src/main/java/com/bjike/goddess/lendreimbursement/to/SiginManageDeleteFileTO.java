package com.bjike.goddess.lendreimbursement.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 借款报销
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-03-20T20:13:56.348 ]
 * @Description: [ 借款报销管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SiginManageDeleteFileTO extends BaseTO {

    public interface TestDEL {
    }

    /**
     * 删除路径
     */
    @NotNull(groups = {SiginManageDeleteFileTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}