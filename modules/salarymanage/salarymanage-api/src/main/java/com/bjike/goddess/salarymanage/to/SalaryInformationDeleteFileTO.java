package com.bjike.goddess.salarymanage.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 薪资管理薪资资料
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:13:56.348 ]
 * @Description: [ 薪资管理薪资资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryInformationDeleteFileTO extends BaseTO {

    public interface TestDEL {
    }

    /**
     * 删除路径
     */
    @NotNull(groups = {SalaryInformationDeleteFileTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}