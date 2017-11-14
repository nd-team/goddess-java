package com.bjike.goddess.employeecontract.to;

import javax.validation.constraints.NotNull;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-11-09 11:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ContractInformationDeleteFileTO {

    public interface TestDEL {
    }

    /**
     * 删除路径
     */
    @NotNull(groups = {ContractInformationDeleteFileTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}
