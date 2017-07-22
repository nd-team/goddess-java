package com.bjike.goddess.staffactivity.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 活动评价数据传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:23 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ActivityEvaluateDTO extends BaseDTO {
    public interface COUNT {
    }

    /**
     * 活动方案名称
     */
    @NotNull(groups = {ActivityEvaluateDTO.COUNT.class}, message = "活动方案名称不能为空")
    private String[] schemes;

    public String[] getSchemes() {
        return schemes;
    }

    public void setSchemes(String[] schemes) {
        this.schemes = schemes;
    }
}