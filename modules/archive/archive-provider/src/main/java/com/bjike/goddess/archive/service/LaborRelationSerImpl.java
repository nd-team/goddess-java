package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.LaborRelationBO;
import com.bjike.goddess.archive.dto.LaborRelationDTO;
import com.bjike.goddess.archive.entity.LaborRelation;
import com.bjike.goddess.archive.to.LaborRelationTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 劳动关系类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:05 ]
 * @Description: [ 劳动关系类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class LaborRelationSerImpl extends ServiceImpl<LaborRelation, LaborRelationDTO> implements LaborRelationSer {

    @Override
    public LaborRelationBO save(LaborRelationTO to) throws SerException {
        LaborRelation entity = BeanTransform.copyProperties(to, LaborRelation.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Override
    public LaborRelationBO update(LaborRelationTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                LaborRelation entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, LaborRelationBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public LaborRelationBO delete(String id) throws SerException {
        LaborRelation entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Override
    public LaborRelationBO congeal(String id) throws SerException {
        LaborRelation entity = super.findById(id);
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Override
    public LaborRelationBO thaw(String id) throws SerException {
        LaborRelation entity = super.findById(id);
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, LaborRelationBO.class);
    }

    @Override
    public List<LaborRelationBO> findByStatus(Status status) throws SerException {
        LaborRelationDTO dto = new LaborRelationDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<LaborRelation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, LaborRelationBO.class);
    }

    @Override
    public List<LaborRelationBO> maps(LaborRelationDTO dto) throws SerException {
        dto.getSorts().add("status");
        return BeanTransform.copyProperties(super.findByPage(dto), LaborRelationBO.class);
    }

}