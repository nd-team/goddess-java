package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CollectBO;
import com.bjike.goddess.moneyside.bo.FundEntryBO;
import com.bjike.goddess.moneyside.dto.FundEntryDTO;
import com.bjike.goddess.moneyside.to.CollectTO;
import com.bjike.goddess.moneyside.to.FundEntryTO;

import java.util.List;

/**
 * 资金进入申请业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:05 ]
 * @Description: [ 资金进入申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FundEntryAPI {

    /**
     * 资金进入申请列表总条数
     */
    default Long countFundEntry(FundEntryDTO fundEntryDTO) throws SerException {
        return null;
    }

    /**
     * 一个资金进入申请
     *
     * @return class FundEntryBO
     */
    default FundEntryBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 资金进入申请
     *
     * @param fundEntryDTO 资金进入申请dto
     * @return class FundEntryBO
     * @throws SerException
     */
    default List<FundEntryBO> findListFundEntry(FundEntryDTO fundEntryDTO) throws SerException {
        return null;
    }

    /**
     * 添加资金进入申请
     *
     * @param fundEntryTO 资金进入申请数据to
     * @return class FundEntryBO
     * @throws SerException
     */
    default FundEntryBO insertFundEntry(FundEntryTO fundEntryTO) throws SerException {
        return null;
    }

    /**
     * 编辑资金进入申请
     *
     * @param fundEntryTO 资金进入申请数据to
     * @return class FundEntryBO
     * @throws SerException
     */
    default FundEntryBO editFundEntry(FundEntryTO fundEntryTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除资金进入申请
     *
     * @param id
     * @throws SerException
     */
    default void removeFundEntry(String id) throws SerException {

    }
    /**
     * 审核
     *
     * @param fundEntryTO
     * @return class FundEntryBO
     * @throws SerException
     */
    default FundEntryBO audit (FundEntryTO fundEntryTO) throws SerException {
        return null;
    }
    /**
     * 汇总
     *
     * @param to 查询条件封装类
     * @return 汇总结果集
     */
    default List<CollectBO> collect(CollectTO to) throws SerException{
        return null;
    }
    

}