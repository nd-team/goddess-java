package com.bjike.goddess.deviceinventory.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.deviceinventory.bo.InventoryRecordBO;
import com.bjike.goddess.deviceinventory.dto.InventoryRecordDTO;
import com.bjike.goddess.deviceinventory.entity.InventoryRecord;
import com.bjike.goddess.deviceinventory.to.InventoryRecordTO;

import java.util.List;

/**
 * 盘点记录业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 盘点记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InventoryRecordSer extends Ser<InventoryRecord, InventoryRecordDTO> {
    /**
     * 添加
     *
     * @param to 盘点记录信息
     * @throws SerException
     */
    default void save(InventoryRecordTO to) throws SerException {
    }

    /**
     * 盘点记录列表
     *
     * @param dto 盘点记录dto
     * @return class InventoryRecordBO
     * @throws SerException
     */
    default List<InventoryRecordBO> list(InventoryRecordDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class InventoryRecordBO
     * @throws SerException
     */
    default List<InventoryRecordBO> export(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 地区汇总
     *
     * @return class InventoryRecordBO
     * @throws SerException
     */
    default List<InventoryRecordBO> areaCount() throws SerException {
        return null;
    }

    /**
     * 部门汇总
     *
     * @return class InventoryRecordBO
     * @throws SerException
     */
    default List<InventoryRecordBO> projectGroupCount() throws SerException {
        return null;
    }

    /**
     * 物资名称汇总
     *
     * @return class InventoryRecordBO
     * @throws SerException
     */
    default List<InventoryRecordBO> materialNameCount() throws SerException {
        return null;
    }

    /**
     * 状态汇总
     *
     * @return class InventoryRecordBO
     * @throws SerException
     */
    default List<InventoryRecordBO> stateCount() throws SerException {
        return null;
    }

    /**
     * 查找盘点记录总条数
     *
     * @param dto 盘点记录dto
     * @throws SerException
     */
    default Long count(InventoryRecordDTO dto) throws SerException {
        return null;
    }
}