package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.DebtBO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.entity.Debt;
import com.bjike.goddess.reportmanagement.to.DebtTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 负债表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class DebtSerImpl extends ServiceImpl<Debt, DebtDTO> implements DebtSer {
    @Override
    public DebtBO save(DebtTO to) throws SerException {
        Debt entity = BeanTransform.copyProperties(to, Debt.class, true);
        return BeanTransform.copyProperties(entity, DebtBO.class);
    }
}