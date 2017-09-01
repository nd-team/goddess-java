package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransferBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransferDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransfer;
import com.bjike.goddess.shareholdersmanage.to.EquityTransferTO;

import java.util.List;

/**
 * 股权转让业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:32 ]
 * @Description: [ 股权转让业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EquityTransferSer extends Ser<EquityTransfer, EquityTransferDTO> {
    /**
     * 股权转让列表总条数
     */
    default Long countEquityfer(EquityTransferDTO equityTransferDTO) throws SerException {
        return null;
    }

    /**
     * 一个股权转让
     *
     * @return class EquityTransferBO
     */
    default EquityTransferBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股权转让列表
     *
     * @param equityTransferDTO 股权转让dto
     * @return class EquityTransferBO
     * @throws SerException
     */
    default List<EquityTransferBO> findList(EquityTransferDTO equityTransferDTO) throws SerException {
        return null;
    }

    /**
     * 股权转让添加
     *
     * @param equityTransferTO 股权转让数据to
     * @throws SerException
     */
    default EquityTransferBO save(EquityTransferTO equityTransferTO) throws SerException {
        return null;
    }

    /**
     * 股权转让编辑
     *
     * @param equityTransferTO 股权转让数据to
     * @throws SerException
     */
    default EquityTransferBO edit(EquityTransferTO equityTransferTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除股权转让
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
}