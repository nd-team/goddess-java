package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.FundEntryConfirmedBO;
import com.bjike.goddess.moneyside.dto.FundEntryConfirmedDTO;
import com.bjike.goddess.moneyside.entity.FundEntryConfirmed;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;

import java.util.List;

/**
 * 资金进入申请已确认业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:08 ]
 * @Description: [ 资金进入申请已确认业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FundEntryConfirmedSer extends Ser<FundEntryConfirmed, FundEntryConfirmedDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 资金进入申请已确认列表总条数
     */
    default Long countFundEntryConfirmed(FundEntryConfirmedDTO fundEntryConfirmedDTO) throws SerException {
        return null;
    }

    /**
     * 一个资金进入申请已确认
     *
     * @return class FundEntryConfirmedBO
     */
    default FundEntryConfirmedBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 资金进入申请已确认
     *
     * @param fundEntryConfirmedDTO 资金进入申请已确认dto
     * @return class FundEntryConfirmedBO
     * @throws SerException
     */
    default List<FundEntryConfirmedBO> findListFundEntryConfirmed(FundEntryConfirmedDTO fundEntryConfirmedDTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除资金进入申请已确认
     *
     * @param id
     * @throws SerException
     */
    default void removeFundEntryConfirmed(String id) throws SerException {

    }
}