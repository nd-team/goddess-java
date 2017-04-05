package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.DimensionBO;
import com.bjike.goddess.organize.dto.DimensionDTO;
import com.bjike.goddess.organize.entity.Dimension;
import com.bjike.goddess.organize.to.DimensionTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 维度业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:24]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("dimensionSer")

public class DimensionSerImpl extends ServiceImpl<Dimension, DimensionDTO> implements DimensionSer {


    @Override
    public List<DimensionBO> findStatus() throws SerException {
        DimensionDTO dto = new DimensionDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Dimension> list = super.findByCis(dto, false);
        List<DimensionBO> bos = BeanTransform.copyProperties(list, DimensionBO.class);
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DimensionBO save(DimensionTO to) throws SerException {
        Dimension dimension = BeanTransform.copyProperties(to, Dimension.class);
        dimension.setStatus(Status.THAW);
        dimension.setCreateTime(LocalDateTime.now());
        super.save(dimension);
        return BeanTransform.copyProperties(dimension, DimensionBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DimensionBO update(DimensionTO to) throws SerException {
        Dimension dimension = super.findById(to.getId());
        dimension.setName(to.getName());
        dimension.setDescription(to.getDescription());
        super.update(dimension);
        return BeanTransform.copyProperties(dimension, DimensionBO.class);
    }
}
