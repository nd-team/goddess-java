package com.bjike.goddess.datastore.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 数据存储
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-20T20:13:56.348 ]
 * @Description: [ 数据存储 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DataStoreDeleteFileTO extends BaseTO {

    public interface TestDEL{}

    /**
     * 删除路径
     */
    @NotNull(groups = {DataStoreDeleteFileTO.TestDEL.class},message = "删除路径不能为空")
    private String[] paths;

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }
}