package com.bjike.goddess.rotation.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.rotation.enums.CollectDetailsType;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-09 15:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RotationCollectDTO extends BaseDTO{

    /**
     * 类型
     */
    private CollectDetailsType collectDetailsType;

    public CollectDetailsType getCollectDetailsType() {
        return collectDetailsType;
    }

    public void setCollectDetailsType(CollectDetailsType collectDetailsType) {
        this.collectDetailsType = collectDetailsType;
    }
}
