package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.FacilityBO;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.dto.FacilityDTO;
import com.bjike.goddess.projectroyalty.entity.Facility;
import com.bjike.goddess.projectroyalty.to.FacilityTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 难易度业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:47 ]
 * @Description: [ 难易度业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class FacilitySerImpl extends ServiceImpl<Facility, FacilityDTO> implements FacilitySer {

    @Override
    public FacilityBO save(FacilityTO to) throws SerException {
        Facility entity = BeanTransform.copyProperties(to, Facility.class);
        FacilityDTO dto = new FacilityDTO();
        dto.getConditions().add(Restrict.eq("facility", to.getFacility()));
        if (super.count(dto) != 0)
            throw new SerException(to.getFacility() + ":已存在");
        super.save(entity);
        return BeanTransform.copyProperties(entity, FacilityBO.class);
    }

    @Override
    public FacilityBO update(FacilityTO to) throws SerException {
        Facility entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        if (to.getFacility().doubleValue() != entity.getFacility().doubleValue()) {
            FacilityDTO dto = new FacilityDTO();
            dto.getConditions().add(Restrict.eq("facility", to.getFacility()));
            if (super.count(dto) != 0)
                throw new SerException(to.getFacility() + ":已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, FacilityBO.class);
    }

    @Override
    public FacilityBO delete(String id) throws SerException {
        Facility entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, FacilityBO.class);
    }

    @Override
    public FacilityBO getById(String id) throws SerException {
        Facility entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, FacilityBO.class);
    }

    @Override
    public List<FacilityBO> maps(FacilityDTO dto) throws SerException {
        dto.getSorts().add("facility=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), FacilityBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        FacilityDTO dto = new FacilityDTO();
        return super.count(dto);
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        List<OpinionBO> bos = new ArrayList<>(0);
        List<Facility> list = super.findAll();
        for (Facility entity : list)
            bos.add(new OpinionBO(entity.getId(), entity.getFacility().toString()));
        return bos;
    }
}