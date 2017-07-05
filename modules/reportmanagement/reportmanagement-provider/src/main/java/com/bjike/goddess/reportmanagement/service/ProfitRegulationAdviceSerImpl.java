package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.ProfitRegulationAdviceBO;
import com.bjike.goddess.reportmanagement.dto.ProfitRegulationAdviceDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitRegulationAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitRegulationAdvice;
import com.bjike.goddess.reportmanagement.entity.ProfitRegulationAdvice;
import com.bjike.goddess.reportmanagement.to.ProfitRegulationAdviceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 利润增减率分析管理建议设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 06:53 ]
 * @Description: [ 利润增减率分析管理建议设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class ProfitRegulationAdviceSerImpl extends ServiceImpl<ProfitRegulationAdvice, ProfitRegulationAdviceDTO> implements ProfitRegulationAdviceSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ProfitRegulationAdviceBO save(ProfitRegulationAdviceTO to) throws SerException {
        ProfitRegulationAdvice entity = BeanTransform.copyProperties(to, ProfitRegulationAdvice.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ProfitRegulationAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProfitRegulationAdviceTO to) throws SerException {
        ProfitRegulationAdvice entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, ProfitRegulationAdvice.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<ProfitRegulationAdviceBO> list(ProfitRegulationAdviceDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<ProfitRegulationAdvice> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ProfitRegulationAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        ProfitRegulationAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public ProfitRegulationAdviceBO findByID(String id) throws SerException {
        ProfitRegulationAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ProfitRegulationAdviceBO.class);
    }

    @Override
    public Long count(ProfitRegulationAdviceDTO dto) throws SerException {
        return super.count(dto);
    }
}