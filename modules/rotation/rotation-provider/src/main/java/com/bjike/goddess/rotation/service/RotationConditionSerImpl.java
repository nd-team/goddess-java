package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.bo.RotationConditionBO;
import com.bjike.goddess.rotation.dto.RotationConditionDTO;
import com.bjike.goddess.rotation.entity.RotationCondition;
import com.bjike.goddess.rotation.to.RotationConditionTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位轮换条件业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:41 ]
 * @Description: [ 岗位轮换条件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class RotationConditionSerImpl extends ServiceImpl<RotationCondition, RotationConditionDTO> implements RotationConditionSer {

    @Override
    public RotationConditionBO save(RotationConditionTO to) throws SerException {
        RotationCondition entity = BeanTransform.copyProperties(to, RotationCondition.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, RotationConditionBO.class);
    }

    @Override
    public RotationConditionBO update(RotationConditionTO to) throws SerException {
        RotationCondition entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, RotationConditionBO.class);
    }

    @Override
    public RotationConditionBO delete(String id) throws SerException {
        RotationCondition entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, RotationConditionBO.class);
    }

    @Override
    public RotationConditionBO getById(String id) throws SerException {
        RotationCondition entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, RotationConditionBO.class);
    }

    @Override
    public List<RotationConditionBO> maps(RotationConditionDTO dto) throws SerException {
        dto.getSorts().add("way=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), RotationConditionBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        RotationConditionDTO dto = new RotationConditionDTO();
        return super.count(dto);
    }
}