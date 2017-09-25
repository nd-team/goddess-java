package com.bjike.goddess.salarymanage.to;

import javax.validation.constraints.NotNull;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-16 13:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SalaryConfirmRecordDeleteFileTO {

    public interface TestDEL {
    }

    /**
     * 删除路径
     */
    @NotNull(groups = {SalaryConfirmRecordDeleteFileTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}
