package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.SocialSecurityTypeBO;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.dto.SocialSecurityTypeDTO;
import com.bjike.goddess.archive.entity.SocialSecurityType;
import com.bjike.goddess.archive.to.SocialSecurityTypeTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公司社保购买类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 公司社保购买类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class SocialSecurityTypeSerImpl extends ServiceImpl<SocialSecurityType, SocialSecurityTypeDTO> implements SocialSecurityTypeSer {

    @Autowired
    private PersonnelQualificationSer personnelQualificationSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SocialSecurityTypeBO save(SocialSecurityTypeTO to) throws SerException {
        SocialSecurityType entity = BeanTransform.copyProperties(to, SocialSecurityType.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SocialSecurityTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SocialSecurityTypeBO update(SocialSecurityTypeTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                SocialSecurityType entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, SocialSecurityTypeBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public SocialSecurityTypeBO delete(String id) throws SerException {
        SocialSecurityType entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        PersonnelQualificationDTO dto = new PersonnelQualificationDTO();
        dto.getConditions().add(Restrict.eq("social.id", entity.getId()));
        if (personnelQualificationSer.findByCis(dto).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SocialSecurityTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SocialSecurityTypeBO congeal(String id) throws SerException {
        SocialSecurityType entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SocialSecurityTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SocialSecurityTypeBO thaw(String id) throws SerException {
        SocialSecurityType entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SocialSecurityTypeBO.class);
    }

    @Override
    public List<SocialSecurityTypeBO> findByStatus(Status status) throws SerException {
        SocialSecurityTypeDTO dto = new SocialSecurityTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<SocialSecurityType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, SocialSecurityTypeBO.class);
    }

    @Override
    public List<SocialSecurityTypeBO> maps(SocialSecurityTypeDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), SocialSecurityTypeBO.class);
    }

    @Override
    public SocialSecurityTypeBO getById(String id) throws SerException {
        SocialSecurityType entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, SocialSecurityTypeBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        SocialSecurityTypeDTO dto = new SocialSecurityTypeDTO();
        return super.count(dto);
    }

}