package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.RepayAnalyzeAdviceBO;
import com.bjike.goddess.reportmanagement.dto.RepayAnalyzeAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.RepayAnalyzeAdvice;
import com.bjike.goddess.reportmanagement.to.RepayAnalyzeAdviceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 偿还能力分析管理建议设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:04 ]
 * @Description: [ 偿还能力分析管理建议设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class RepayAnalyzeAdviceSerImpl extends ServiceImpl<RepayAnalyzeAdvice, RepayAnalyzeAdviceDTO> implements RepayAnalyzeAdviceSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RepayAnalyzeAdviceBO save(RepayAnalyzeAdviceTO to) throws SerException {
        RepayAnalyzeAdvice entity = BeanTransform.copyProperties(to, RepayAnalyzeAdvice.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, RepayAnalyzeAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(RepayAnalyzeAdviceTO to) throws SerException {
        RepayAnalyzeAdvice entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, RepayAnalyzeAdvice.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<RepayAnalyzeAdviceBO> list(RepayAnalyzeAdviceDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<RepayAnalyzeAdvice> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, RepayAnalyzeAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        RepayAnalyzeAdvice entity = super.findById(id);
        if (entity==null){
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public RepayAnalyzeAdviceBO findByID(String id) throws SerException {
        RepayAnalyzeAdvice entity = super.findById(id);
        if (entity==null){
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, RepayAnalyzeAdviceBO.class);
    }

    @Override
    public Long count(RepayAnalyzeAdviceDTO dto) throws SerException {
        return super.count(dto);
    }
}