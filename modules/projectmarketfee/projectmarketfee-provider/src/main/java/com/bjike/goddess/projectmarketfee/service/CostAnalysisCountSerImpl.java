package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisCountBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisCountDTO;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysisCount;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisCountTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 费用效益分析业务汇总业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 03:13 ]
 * @Description: [ 费用效益分析业务汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmarketfeeSerCache")
@Service
public class CostAnalysisCountSerImpl extends ServiceImpl<CostAnalysisCount, CostAnalysisCountDTO> implements CostAnalysisCountSer {
    @Override
    public CostAnalysisCountBO save(CostAnalysisCountTO to) throws SerException {
        CostAnalysisCount c = BeanTransform.copyProperties(to, CostAnalysisCount.class, true);
        super.save(c);
        return BeanTransform.copyProperties(c, CostAnalysisCountBO.class);
    }

    @Override
    public CostAnalysisCountBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), CostAnalysisCountBO.class);
    }
}