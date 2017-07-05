package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.DebtStructureAdviceBO;
import com.bjike.goddess.reportmanagement.dto.DebtStructureAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.DebtStructureAdvice;
import com.bjike.goddess.reportmanagement.to.DebtStructureAdviceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 负债与权益结构管理建议设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:56 ]
 * @Description: [ 负债与权益结构管理建议设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class DebtStructureAdviceSerImpl extends ServiceImpl<DebtStructureAdvice, DebtStructureAdviceDTO> implements DebtStructureAdviceSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public DebtStructureAdviceBO save(DebtStructureAdviceTO to) throws SerException {
        DebtStructureAdvice entity = BeanTransform.copyProperties(to, DebtStructureAdvice.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DebtStructureAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(DebtStructureAdviceTO to) throws SerException {
        DebtStructureAdvice entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, DebtStructureAdvice.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<DebtStructureAdviceBO> list(DebtStructureAdviceDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<DebtStructureAdvice> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, DebtStructureAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        DebtStructureAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public DebtStructureAdviceBO findByID(String id) throws SerException {
        DebtStructureAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, DebtStructureAdviceBO.class);
    }

    @Override
    public Long count(DebtStructureAdviceDTO dto) throws SerException {
        return super.count(dto);
    }
}