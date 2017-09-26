package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProblemTypeTO extends BaseTO {
    /**
     * 类型名
     */
    @NotBlank(message = "类型名不能为空", groups = {ADD.class, EDIT.class})
    private String name;
    /**
     * 是否开启
     */
    private Boolean enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
