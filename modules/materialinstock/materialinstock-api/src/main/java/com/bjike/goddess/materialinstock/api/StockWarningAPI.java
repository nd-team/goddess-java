package com.bjike.goddess.materialinstock.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialinstock.bo.StockWarningBO;
import com.bjike.goddess.materialinstock.dto.StockWarningDTO;
import com.bjike.goddess.materialinstock.excel.SonPermissionObject;
import com.bjike.goddess.materialinstock.to.GuidePermissionTO;
import com.bjike.goddess.materialinstock.to.StockWarningTO;

import java.util.List;

/**
 * 库存预警业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 05:00 ]
 * @Description: [ 库存预警业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StockWarningAPI {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 根据id查询库存预警
     *
     * @param id 库存预警唯一标识
     * @return class StockWarningBO
     * @throws SerException
     */
    StockWarningBO findById(String id) throws SerException;

    /**
     * 查询库存预警记录条数
     *
     * @param dto 库存预警dto
     * @throws SerException
     */
    Long count(StockWarningDTO dto) throws SerException;

    /**
     * 分页查询库存预警
     *
     * @return class StockWarningBO
     * @throws SerException
     */
    List<StockWarningBO> list(StockWarningDTO dto) throws SerException;

    /**
     * 保存库存预警
     *
     * @param to 库存预警to
     * @return class StockWarningBO
     * @throws SerException
     */
    StockWarningBO save(StockWarningTO to) throws SerException;

    /**
     * 根据id删除库存预警
     *
     * @param id 库存预警唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新库存预警
     *
     * @param to 库存预警to
     * @throws SerException
     */
    void update(StockWarningTO to) throws SerException;

}