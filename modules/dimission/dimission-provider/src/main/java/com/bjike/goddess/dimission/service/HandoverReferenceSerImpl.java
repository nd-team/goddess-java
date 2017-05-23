package com.bjike.goddess.dimission.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.bo.HandoverReferenceBO;
import com.bjike.goddess.dimission.dto.HandoverReferenceDTO;
import com.bjike.goddess.dimission.entity.HandoverReference;
import com.bjike.goddess.dimission.to.HandoverReferenceTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 交接信息参考业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:36 ]
 * @Description: [ 交接信息参考业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dimissionSerCache")
@Service
public class HandoverReferenceSerImpl extends ServiceImpl<HandoverReference, HandoverReferenceDTO> implements HandoverReferenceSer {

    @Override
    public HandoverReferenceBO save(HandoverReferenceTO to) throws SerException {
        HandoverReference entity = BeanTransform.copyProperties(to, HandoverReference.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, HandoverReferenceBO.class);
    }

    @Override
    public HandoverReferenceBO update(HandoverReferenceTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            HandoverReference entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, HandoverReferenceBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不能为空");
        }
    }

    @Override
    public HandoverReferenceBO delete(String id) throws SerException {
        HandoverReference entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, HandoverReferenceBO.class);
    }

    @Override
    public List<HandoverReferenceBO> maps(HandoverReferenceDTO dto) throws SerException {
        dto.getSorts().add("modifyTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), HandoverReferenceBO.class);
    }

    @Override
    public HandoverReferenceBO getById(String id) throws SerException {
        HandoverReference entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, HandoverReferenceBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        HandoverReferenceDTO dto = new HandoverReferenceDTO();
        return super.count(dto);
    }
}