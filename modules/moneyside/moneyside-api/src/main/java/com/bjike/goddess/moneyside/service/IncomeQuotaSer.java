package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.IncomeDistributionBO;
import com.bjike.goddess.moneyside.bo.IncomeQuotaBO;
import com.bjike.goddess.moneyside.dto.IncomeDistributionDTO;
import com.bjike.goddess.moneyside.dto.IncomeQuotaDTO;
import com.bjike.goddess.moneyside.entity.IncomeQuota;
import com.bjike.goddess.moneyside.to.IncomeDistributionTO;
import com.bjike.goddess.moneyside.to.IncomeQuotaTO;

import java.util.List;

/**
 * 收益分配额业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:28 ]
 * @Description: [ 收益分配额业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IncomeQuotaSer extends Ser<IncomeQuota, IncomeQuotaDTO> {
    /**
     * 收益分配额列表总条数
     */
    default Long countIncomeQuota(IncomeQuotaDTO incomeQuotaDTO) throws SerException {
        return null;
    }

    /**
     * 一个收益分配额
     *
     * @return class IncomeQuotaBO
     */
    default IncomeQuotaBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 收益分配额
     *
     * @param incomeQuotaDTO 收益分配额dto
     * @return class IncomeQuotaBO
     * @throws SerException
     */
    default List<IncomeQuotaBO> findListIncomeQuota(IncomeQuotaDTO incomeQuotaDTO) throws SerException {
        return null;
    }

    /**
     * 添加收益分配额
     *
     * @param incomeQuotaTO 收益分配额数据to
     * @return class IncomeQuotaBO
     * @throws SerException
     */
    default IncomeQuotaBO insertIncomeQuota(IncomeQuotaTO incomeQuotaTO) throws SerException {
        return null;
    }

    /**
     * 编辑收益分配额
     *
     * @param incomeQuotaTO 收益分配额数据to
     * @return class IncomeQuotaBO
     * @throws SerException
     */
    default IncomeQuotaBO editIncomeQuota(IncomeQuotaTO incomeQuotaTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除收益分配额
     *
     * @param id
     * @throws SerException
     */
    default void removeIncomeQuota(String id) throws SerException {

    }
}