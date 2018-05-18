package com.bjike.goddess.task.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ValDTO extends BaseDTO {
    private List<String> values;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
