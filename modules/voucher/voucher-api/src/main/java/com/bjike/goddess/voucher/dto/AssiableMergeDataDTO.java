package com.bjike.goddess.voucher.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-28 16:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public class AssiableMergeDataDTO extends BaseDTO{

    private boolean success;

    public AssiableMergeDataDTO(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
