package com.bjike.goddess.deviceinventory.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.deviceinventory.bo.InventoryBO;
import com.bjike.goddess.deviceinventory.dto.InventoryDTO;
import com.bjike.goddess.deviceinventory.to.GuidePermissionTO;
import com.bjike.goddess.deviceinventory.to.InventoryTO;
import com.bjike.goddess.deviceinventory.vo.SonPermissionObject;

import java.util.List;
import java.util.Set;

/**
 * 盘点业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InventoryAPI {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

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
     * 根据时间段导出excel
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws SerException
     */
    byte[] export(String startTime, String endTime) throws SerException;

    /**
     * 查询盘点总记录数
     *
     * @param dto 盘点dto
     * @throws SerException
     */
    default Long count(InventoryDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找所有入库编号
     *
     * @return
     * @throws SerException
     */
    Set<String> allstockEncoding() throws SerException;
}