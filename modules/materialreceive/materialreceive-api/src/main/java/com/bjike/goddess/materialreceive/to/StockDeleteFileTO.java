package com.bjike.goddess.materialreceive.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * @Author: [lijuntao]
 * @Date: [2017-07-14 16:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StockDeleteFileTO extends BaseTO {
    public interface TestDEL {
    }

    /**
     * 删除路径
     */
    @NotNull(groups = {StockDeleteFileTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}
