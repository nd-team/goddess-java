package com.bjike.goddess.deviceinventory.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.deviceinventory.bo.InventoryBO;
import com.bjike.goddess.deviceinventory.dto.InventoryDTO;
import com.bjike.goddess.deviceinventory.entity.Inventory;
import com.bjike.goddess.deviceinventory.to.InventoryTO;

import java.util.List;

/**
 * 盘点业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InventorySer extends Ser<Inventory, InventoryDTO> {
    /**
     * 通过id查找
     *
     * @param id 盘点id
     * @return class InventoryBO
     * @throws SerException
     */
    default InventoryBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 盘点列表
     *
     * @param dto 盘点dto
     * @return class InventoryBO
     * @throws SerException
     */
    default List<InventoryBO> list(InventoryDTO dto) throws SerException {
        return null;
    }

    /**
     * 盘点
     *
     * @param to 盘点to
     * @throws SerException
     */
    default void inventory(InventoryTO to) throws SerException {
    }

    /**
     * 导出
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class InventoryBO
     * @throws SerException
     */
    default List<InventoryBO> export(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 查询盘点总记录数
     *
     * @param dto 盘点dto
     * @throws SerException
     */
    default Long count(InventoryDTO dto) throws SerException {
        return null;
    }
}