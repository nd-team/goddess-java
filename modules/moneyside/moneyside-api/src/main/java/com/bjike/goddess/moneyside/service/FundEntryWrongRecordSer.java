package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.FundEntryBO;
import com.bjike.goddess.moneyside.bo.FundEntryWrongRecordBO;
import com.bjike.goddess.moneyside.dto.FundEntryDTO;
import com.bjike.goddess.moneyside.dto.FundEntryWrongRecordDTO;
import com.bjike.goddess.moneyside.entity.FundEntryWrongRecord;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;

import java.util.List;

/**
 * 资金进入申请有误记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:07 ]
 * @Description: [ 资金进入申请有误记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FundEntryWrongRecordSer extends Ser<FundEntryWrongRecord, FundEntryWrongRecordDTO> {
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
     * 资金进入申请有误记录列表总条数
     */
    default Long countFundEntryWrongRecord(FundEntryWrongRecordDTO fundEntryWrongRecordDTO) throws SerException {
        return null;
    }

    /**
     * 一个资金进入申请有误记录
     *
     * @return class FundEntryWrongRecordBO
     */
    default FundEntryWrongRecordBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 资金进入申请有误记录
     *
     * @param fundEntryWrongRecordDTO 资金进入申请有误记录dto
     * @return class FundEntryWrongRecordBO
     * @throws SerException
     */
    default List<FundEntryWrongRecordBO> findListFundEntryWrongRecord(FundEntryWrongRecordDTO fundEntryWrongRecordDTO) throws SerException {
        return null;
    }
    /**
     * 根据id删除资金进入申请有误记录
     *
     * @param id
     * @throws SerException
     */
    default void removeFundEntryWrongRecord(String id) throws SerException {

    }
}