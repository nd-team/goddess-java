package com.bjike.goddess.dispatchcar.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.dispatchcar.enums.CollectIntervalType;

import javax.validation.constraints.NotNull;

/**
 * 定时器汇总类型封装
 *
 * @Author: [Jason]
 * @Date: [17-5-11 上午9:30]
 * @Description: [定时器汇总类型封装]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectIntervalTypeTO {

    /**
     * 汇总日期间隔
     */
    @NotNull(message = "汇总日期间隔不能为空", groups = {ADD.class})
    private CollectIntervalType type;

    public CollectIntervalType getType() {
        return type;
    }

    public void setType(CollectIntervalType type) {
        this.type = type;
    }
}
