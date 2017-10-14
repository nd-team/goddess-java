package com.bjike.goddess.rentcar.to;

import javax.validation.constraints.NotNull;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-10-09 10:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DriverInforDeleteFileTO {
    public interface TestDEL {
    }

    /**
     * 删除路径
     */
    @NotNull(groups = {DriverInforDeleteFileTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}

