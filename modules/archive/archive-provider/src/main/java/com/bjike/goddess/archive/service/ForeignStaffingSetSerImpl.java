package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ForeignStaffingSetBO;
import com.bjike.goddess.archive.dto.ForeignStaffingSetDTO;
import com.bjike.goddess.archive.entity.ForeignStaffingSet;
import com.bjike.goddess.archive.to.ForeignStaffingSetTO;
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
 * 对外人员基本信息设置业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:28 ]
 * @Description: [ 对外人员基本信息设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ForeignStaffingSetSerImpl extends ServiceImpl<ForeignStaffingSet, ForeignStaffingSetDTO> implements ForeignStaffingSetSer {

    @Override
    public ForeignStaffingSetBO save(ForeignStaffingSetTO to) throws SerException {
        ForeignStaffingSet entity = BeanTransform.copyProperties(to, ForeignStaffingSet.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Override
    public ForeignStaffingSetBO update(ForeignStaffingSetTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ForeignStaffingSet entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public ForeignStaffingSetBO delete(String id) throws SerException {
        ForeignStaffingSet entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Override
    public ForeignStaffingSetBO congeal(String id) throws SerException {
        ForeignStaffingSet entity = super.findById(id);
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Override
    public ForeignStaffingSetBO thaw(String id) throws SerException {
        ForeignStaffingSet entity = super.findById(id);
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ForeignStaffingSetBO.class);
    }

    @Override
    public List<ForeignStaffingSetBO> findByStatus(Status status) throws SerException {
        ForeignStaffingSetDTO dto = new ForeignStaffingSetDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<ForeignStaffingSet> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ForeignStaffingSetBO.class);
    }

    @Override
    public List<ForeignStaffingSetBO> maps(ForeignStaffingSetDTO dto) throws SerException {
        dto.getSorts().add("status");
        return BeanTransform.copyProperties(super.findByPage(dto), ForeignStaffingSetBO.class);
    }

}