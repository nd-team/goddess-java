package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.ProfitIndicatorAdviceBO;
import com.bjike.goddess.reportmanagement.dto.ProfitIndicatorAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitIndicatorAdvice;
import com.bjike.goddess.reportmanagement.to.ProfitIndicatorAdviceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 利润分析指标管理建议设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 05:24 ]
 * @Description: [ 利润分析指标管理建议设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class ProfitIndicatorAdviceSerImpl extends ServiceImpl<ProfitIndicatorAdvice, ProfitIndicatorAdviceDTO> implements ProfitIndicatorAdviceSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ProfitIndicatorAdviceBO save(ProfitIndicatorAdviceTO to) throws SerException {
        ProfitIndicatorAdvice entity = BeanTransform.copyProperties(to, ProfitIndicatorAdvice.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ProfitIndicatorAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProfitIndicatorAdviceTO to) throws SerException {
        ProfitIndicatorAdvice entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, ProfitIndicatorAdvice.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<ProfitIndicatorAdviceBO> list(ProfitIndicatorAdviceDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<ProfitIndicatorAdvice> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, ProfitIndicatorAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        ProfitIndicatorAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public ProfitIndicatorAdviceBO findByID(String id) throws SerException {
        ProfitIndicatorAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ProfitIndicatorAdviceBO.class);
    }

    @Override
    public Long count(ProfitIndicatorAdviceDTO dto) throws SerException {
        return super.count(dto);
    }
}