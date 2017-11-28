package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectprocing.bo.SettlementProcessBO;
import com.bjike.goddess.projectprocing.dto.SettlementProcessDTO;
import com.bjike.goddess.projectprocing.entity.SettlementProcess;
import com.bjike.goddess.projectprocing.to.SettlementProcessTO;

import java.util.List;

/**
 * 结算流程存储记录业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SettlementProcessSer extends Ser<SettlementProcess, SettlementProcessDTO> {
    /**
     * 结算流程存储记录总条数
     */
    default Long countSetProcess(SettlementProcessDTO settlementProcessDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取结算流程存储记录
     *
     * @return class SettlementProcessBO
     */
    default SettlementProcessBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 结算流程存储记录列表
     *
     * @return class SettlementProcessBO
     */
    default List<SettlementProcessBO> listSetProcess(SettlementProcessDTO settlementProcessDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param settlementProcessTO 结算流程存储记录
     * @return class SettlementProcessBO
     */
    default SettlementProcessBO addSetProcess(SettlementProcessTO settlementProcessTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param settlementProcessTO 结算流程存储记录
     * @return class SettlementProcessBO
     */
    default SettlementProcessBO editSetProcess(SettlementProcessTO settlementProcessTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSetProcess(String id) throws SerException {
        return;
    }

    /**
     * 修改是否有结算流程附件字段为是
     *
     * @param id 结算流程存储记录id
     */
    default void updateSettProceAttach(String id) throws SerException {
        return;
    }

    /**
     * 获取所有的外包单位
     *
     */
    default List<String> findOutUnit() throws SerException {
        return null;
    }

}