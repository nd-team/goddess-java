package com.bjike.goddess.workprogress.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * @Author: [dengjunren]
 * @Date: [2017-05-17 17:36]
 * @Description: [ 标准修改传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StandardTO extends BaseTO {

    /**
     * 节点标准
     */
    @NotNull(message = "节点标准不能为空", groups = {ADD.class, EDIT.class})
    private Double standard;

    public Double getStandard() {
        return standard;
    }

    public void setStandard(Double standard) {
        this.standard = standard;
    }
}
