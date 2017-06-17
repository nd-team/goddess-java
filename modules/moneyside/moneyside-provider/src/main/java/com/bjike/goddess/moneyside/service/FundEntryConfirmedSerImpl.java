package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.FundEntryConfirmedBO;
import com.bjike.goddess.moneyside.dto.FundEntryConfirmedDTO;
import com.bjike.goddess.moneyside.entity.FundEntryConfirmed;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资金进入申请已确认业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:08 ]
 * @Description: [ 资金进入申请已确认业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class FundEntryConfirmedSerImpl extends ServiceImpl<FundEntryConfirmed, FundEntryConfirmedDTO> implements FundEntryConfirmedSer {
    @Override
    public Long countFundEntryConfirmed(FundEntryConfirmedDTO fundEntryConfirmedDTO) throws SerException {
        Long count = super.count(fundEntryConfirmedDTO);
        return count;
    }

    @Override
    public FundEntryConfirmedBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        FundEntryConfirmed fundEntryConfirmed = super.findById(id);
        return BeanTransform.copyProperties(fundEntryConfirmed,FundEntryConfirmedBO.class);
    }

    @Override
    public List<FundEntryConfirmedBO> findListFundEntryConfirmed(FundEntryConfirmedDTO fundEntryConfirmedDTO) throws SerException {
        List<FundEntryConfirmed> fundEntryConfirmeds = super.findByPage(fundEntryConfirmedDTO);
        List<FundEntryConfirmedBO> fundEntryConfirmedBOS = BeanTransform.copyProperties(fundEntryConfirmeds,FundEntryConfirmedBO.class);
        return fundEntryConfirmedBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeFundEntryConfirmed(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}