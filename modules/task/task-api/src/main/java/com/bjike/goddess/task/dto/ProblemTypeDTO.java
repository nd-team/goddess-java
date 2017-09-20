package com.bjike.goddess.task.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProblemTypeDTO extends BaseDTO {
    /**
     * 开启状态,空时查询全部
     */
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
