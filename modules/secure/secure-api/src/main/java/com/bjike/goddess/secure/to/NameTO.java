package com.bjike.goddess.secure.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * @Author: [xiazhili]
 * @Date: [2017-09-25 10:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NameTO extends BaseTO {
    public interface TestName{}
    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空",groups = {NameTO.TestName.class})
    private String[] names;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}
