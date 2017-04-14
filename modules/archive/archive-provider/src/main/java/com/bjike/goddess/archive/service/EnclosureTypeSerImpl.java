package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.EnclosureTypeBO;
import com.bjike.goddess.archive.dto.EnclosureTypeDTO;
import com.bjike.goddess.archive.entity.EnclosureType;
import com.bjike.goddess.archive.to.EnclosureTypeTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 附件类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 附件类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class EnclosureTypeSerImpl extends ServiceImpl<EnclosureType, EnclosureTypeDTO> implements EnclosureTypeSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EnclosureTypeBO save(EnclosureTypeTO to) throws SerException {
        EnclosureType entity = BeanTransform.copyProperties(to, EnclosureType.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, EnclosureTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EnclosureTypeBO update(EnclosureTypeTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                EnclosureType entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, EnclosureTypeBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public EnclosureTypeBO delete(String id) throws SerException {
        EnclosureType entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, EnclosureTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EnclosureTypeBO congeal(String id) throws SerException {
        EnclosureType entity = super.findById(id);
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, EnclosureTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EnclosureTypeBO thaw(String id) throws SerException {
        EnclosureType entity = super.findById(id);
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, EnclosureTypeBO.class);
    }

    @Override
    public List<EnclosureTypeBO> findByStatus(Status status) throws SerException {
        EnclosureTypeDTO dto = new EnclosureTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<EnclosureType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, EnclosureTypeBO.class);
    }

    @Override
    public List<EnclosureTypeBO> maps(EnclosureTypeDTO dto) throws SerException {
        dto.getSorts().add("status");
        return BeanTransform.copyProperties(super.findByPage(dto), EnclosureTypeBO.class);
    }
}