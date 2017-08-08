package com.bjike.goddess.version.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.version.bo.AnswerBO;
import com.bjike.goddess.version.dto.AnswerDTO;
import com.bjike.goddess.version.entity.Answer;
import com.bjike.goddess.version.to.AnswerTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 答案业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:10 ]
 * @Description: [ 答案业务实现 ]
 * @Answer: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "versionSerCache")
@Service
public class AnswerSerImpl extends ServiceImpl<Answer, AnswerDTO> implements AnswerSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AnswerBO save(AnswerTO to) throws SerException {
        Answer entity = BeanTransform.copyProperties(to, Answer.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AnswerBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(AnswerTO to) throws SerException {
        Answer entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Answer.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<AnswerBO> list(AnswerDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Answer> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AnswerBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        Answer entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public AnswerBO findByID(String id) throws SerException {
        Answer entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, AnswerBO.class);
    }

    @Override
    public Long count(AnswerDTO dto) throws SerException {
        return super.count(dto);
    }
}