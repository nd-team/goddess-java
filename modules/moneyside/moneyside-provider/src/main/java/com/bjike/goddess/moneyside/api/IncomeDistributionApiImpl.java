package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.IncomeDistributionBO;
import com.bjike.goddess.moneyside.dto.IncomeDistributionDTO;
import com.bjike.goddess.moneyside.entity.IncomeDistribution;
import com.bjike.goddess.moneyside.service.IncomeDistributionSer;
import com.bjike.goddess.moneyside.to.IncomeDistributionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 收益比例分配业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:18 ]
 * @Description: [ 收益比例分配业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("incomeDistributionApiImpl")
public class IncomeDistributionApiImpl implements IncomeDistributionAPI {
    @Autowired
    private IncomeDistributionSer incomeDistributionSer;
    @Override
    public Long countIncomeDistribution(IncomeDistributionDTO incomeDistributionDTO) throws SerException {
        return incomeDistributionSer.countIncomeDistribution(incomeDistributionDTO);
    }

    @Override
    public IncomeDistributionBO getOne(String id) throws SerException {
        return incomeDistributionSer.getOne(id);
    }

    @Override
    public List<IncomeDistributionBO> findListIncomeDistribution(IncomeDistributionDTO incomeDistributionDTO) throws SerException {
        return incomeDistributionSer.findListIncomeDistribution(incomeDistributionDTO);
    }

    @Override
    public IncomeDistributionBO insertIncomeDistribution(IncomeDistributionTO incomeDistributionTO) throws SerException {
        return incomeDistributionSer.insertIncomeDistribution(incomeDistributionTO);
    }

    @Override
    public IncomeDistributionBO editIncomeDistribution(IncomeDistributionTO incomeDistributionTO) throws SerException {
        return incomeDistributionSer.editIncomeDistribution(incomeDistributionTO);
    }

    @Override
    public void removeIncomeDistribution(String id) throws SerException {
        incomeDistributionSer.removeIncomeDistribution(id);
    }
}