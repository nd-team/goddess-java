package com.bjike.goddess.capability.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 公司能力展示
 * @Author: [zhuangkaiqin]
 * @Date 17-6-16下午3:23
 * @Description: [公司能力展示]
 * @Version: [1.0.0]
 */
public class CapabilityDeleteFileTO extends BaseTO {

    public interface TestDEL {
    }

    /**
     * 删除路径
     */
    @NotNull(groups = {CapabilityDeleteFileTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}