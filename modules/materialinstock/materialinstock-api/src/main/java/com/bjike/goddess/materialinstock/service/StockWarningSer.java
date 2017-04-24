package com.bjike.goddess.materialinstock.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialinstock.bo.StockWarningBO;
import com.bjike.goddess.materialinstock.dto.StockWarningDTO;
import com.bjike.goddess.materialinstock.entity.StockWarning;
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
public interface StockWarningSer extends Ser<StockWarning, StockWarningDTO> {

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