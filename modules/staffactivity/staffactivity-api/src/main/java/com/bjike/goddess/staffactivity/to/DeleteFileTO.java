package com.bjike.goddess.staffactivity.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 删除附件路径
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-15 17:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DeleteFileTO extends BaseTO {
    public interface TestDEL {
    }

    /**
     * 删除路径
     */
    @NotNull(groups = {DeleteFileTO.TestDEL.class}, message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}
