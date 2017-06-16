package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.IncomeQuotaBO;
import com.bjike.goddess.moneyside.dto.IncomeQuotaDTO;
import com.bjike.goddess.moneyside.service.IncomeQuotaSer;
import com.bjike.goddess.moneyside.to.IncomeQuotaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收益分配额业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:28 ]
 * @Description: [ 收益分配额业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("incomeQuotaApiImpl")
public class IncomeQuotaApiImpl implements IncomeQuotaAPI {
    @Autowired
    private IncomeQuotaSer incomeQuotaSer;
    @Override
    public Long countIncomeQuota(IncomeQuotaDTO incomeQuotaDTO) throws SerException {
        return incomeQuotaSer.countIncomeQuota(incomeQuotaDTO);
    }

    @Override
    public IncomeQuotaBO getOne(String id) throws SerException {
        return incomeQuotaSer.getOne(id);
    }

    @Override
    public List<IncomeQuotaBO> findListIncomeQuota(IncomeQuotaDTO incomeQuotaDTO) throws SerException {
        return incomeQuotaSer.findListIncomeQuota(incomeQuotaDTO);
    }

    @Override
    public IncomeQuotaBO insertIncomeQuota(IncomeQuotaTO incomeQuotaTO) throws SerException {
        return incomeQuotaSer.insertIncomeQuota(incomeQuotaTO);
    }


    @Override
    public IncomeQuotaBO editIncomeQuota(IncomeQuotaTO incomeQuotaTO) throws SerException {
        return incomeQuotaSer.editIncomeQuota(incomeQuotaTO);
    }

    @Override
    public void removeIncomeQuota(String id) throws SerException {
        incomeQuotaSer.removeIncomeQuota(id);
    }
}