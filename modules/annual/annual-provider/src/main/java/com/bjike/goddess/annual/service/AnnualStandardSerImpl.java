package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualStandardBO;
import com.bjike.goddess.annual.dto.AnnualStandardDTO;
import com.bjike.goddess.annual.entity.AnnualStandard;
import com.bjike.goddess.annual.to.AnnualStandardTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 年假标准业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:26 ]
 * @Description: [ 年假标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "annualSerCache")
@Service
public class AnnualStandardSerImpl extends ServiceImpl<AnnualStandard, AnnualStandardDTO> implements AnnualStandardSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO save(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = BeanTransform.copyProperties(to, AnnualStandard.class);
        entity.setStatus(Status.THAW);
        for (AnnualStandardBO standard : this.findThaw())
            if ((standard.getStartCycle() <= entity.getStartCycle() && standard.getEndCycle() >= entity.getStartCycle())
                    || (standard.getStartCycle() <= entity.getEndCycle() && standard.getEndCycle() >= entity.getEndCycle()))
                throw new SerException("工龄范围标准有重合,请重新输入");
        super.save(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO update(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = BeanTransform.copyProperties(to, AnnualStandard.class);
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO delete(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO congeal(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = super.findById(to.getId());
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualStandardBO thaw(AnnualStandardTO to) throws SerException {
        AnnualStandard entity = super.findById(to.getId());
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, AnnualStandardBO.class);
    }

    @Override
    public List<AnnualStandardBO> findThaw() throws SerException {
        AnnualStandardDTO dto = new AnnualStandardDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<AnnualStandard> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, AnnualStandardBO.class);
    }

    @Override
    public AnnualStandardBO findBySeniority(Integer seniority) throws SerException {
        AnnualStandardDTO dto = new AnnualStandardDTO();
        dto.getConditions().add(Restrict.lt_eq("startCycle", seniority));
        dto.getSorts().add("startCycle=asc");
        List<AnnualStandard> list = super.findByCis(dto);
        if (list.size() == 0)
            return null;
        else
            return BeanTransform.copyProperties(list.get(0), AnnualStandardBO.class);
    }
}