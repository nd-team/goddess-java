package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeCountBO;
import com.bjike.goddess.projectmarketfee.dto.ProjectMarketFeeCountDTO;
import com.bjike.goddess.projectmarketfee.entity.ProjectMarketFeeCount;
import com.bjike.goddess.projectmarketfee.to.ProjectMarketFeeCountTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 项目前期的市场活动费汇总业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 01:50 ]
 * @Description: [ 项目前期的市场活动费汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmarketfeeSerCache")
@Service
public class ProjectMarketFeeCountSerImpl extends ServiceImpl<ProjectMarketFeeCount, ProjectMarketFeeCountDTO> implements ProjectMarketFeeCountSer {
    @Override
    public ProjectMarketFeeCountBO save(ProjectMarketFeeCountTO to) throws SerException {
        ProjectMarketFeeCount p = BeanTransform.copyProperties(to, ProjectMarketFeeCount.class, true);
        return BeanTransform.copyProperties(super.save(p),ProjectMarketFeeCountBO.class);
    }

    @Override
    public ProjectMarketFeeCountBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), ProjectMarketFeeCountBO.class);
    }
}