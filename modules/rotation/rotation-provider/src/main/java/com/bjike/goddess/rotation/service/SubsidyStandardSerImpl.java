package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.bo.SubsidyStandardBO;
import com.bjike.goddess.rotation.dto.SubsidyStandardDTO;
import com.bjike.goddess.rotation.entity.SubsidyStandard;
import com.bjike.goddess.rotation.to.SubsidyStandardTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位补贴标准业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:06 ]
 * @Description: [ 岗位补贴标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class SubsidyStandardSerImpl extends ServiceImpl<SubsidyStandard, SubsidyStandardDTO> implements SubsidyStandardSer {

    @Override
    public SubsidyStandardBO save(SubsidyStandardTO to) throws SerException {
        if (this.findByArrangement(to.getArrangement()) != null)
            throw new SerException("该岗位层级已存在");
        SubsidyStandard entity = BeanTransform.copyProperties(to, SubsidyStandard.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Override
    public SubsidyStandardBO update(SubsidyStandardTO to) throws SerException {
        if (this.findByArrangement(to.getArrangement()) != null)
            throw new SerException("该岗位层级已存在");
        SubsidyStandard entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Override
    public SubsidyStandardBO delete(String id) throws SerException {
        SubsidyStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Override
    public SubsidyStandardBO congeal(String id) throws SerException {
        SubsidyStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Override
    public SubsidyStandardBO thaw(String id) throws SerException {
        SubsidyStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Override
    public SubsidyStandardBO getById(String id) throws SerException {
        SubsidyStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, SubsidyStandardBO.class);
    }

    @Override
    public List<SubsidyStandardBO> maps(SubsidyStandardDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), SubsidyStandardBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        SubsidyStandardDTO dto = new SubsidyStandardDTO();
        return super.count(dto);
    }

    @Override
    public SubsidyStandardBO findByArrangement(String arrangement) throws SerException {
        SubsidyStandardDTO dto = new SubsidyStandardDTO();
        dto.getConditions().add(Restrict.eq("arrangement", arrangement));
        return BeanTransform.copyProperties(super.findOne(dto), SubsidyStandardBO.class);
    }

    @Override
    public List<SubsidyStandardBO> findThaw() throws SerException {
        SubsidyStandardDTO dto = new SubsidyStandardDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        return BeanTransform.copyProperties(super.findByCis(dto), SubsidyStandardBO.class);
    }
}