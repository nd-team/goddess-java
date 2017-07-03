package com.bjike.goddess.deviceinventory.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 盘点数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InventoryDTO extends BaseDTO {
    public interface LIST {
    }

    /**
     * 编号数组
     */
    @NotNull(groups = {InventoryDTO.LIST.class}, message = "编号数组不能为空")
    private String[] stockEncodings;

    public String[] getStockEncodings() {
        return stockEncodings;
    }

    public void setStockEncodings(String[] stockEncodings) {
        this.stockEncodings = stockEncodings;
    }
}