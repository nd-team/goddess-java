package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.dto.PositionDTO;
import com.bjike.goddess.user.entity.Position;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 职位业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class PositionSerImpl extends ServiceImpl<Position, PositionDTO> implements PositionSer {

    @Override
    public PositionBO findBOById(String id) throws SerException {
        Position position = super.findById(id);
        PositionBO bo = BeanTransform.copyProperties(position, PositionBO.class);
        if (null != position.getParent()) {
            Position parent = super.findById(position.getParent().getId());
            bo.setParentName(parent.getName());
            bo.setParentId(parent.getId());
        }
        return bo;
    }

    @Override
    public PositionBO findParent(String id) throws SerException {
        Position position = super.findById(id);
        if (null == position.getParent())
            return null;
        Position parent = super.findById(position.getParent().getId());
        PositionBO bo = BeanTransform.copyProperties(position, PositionBO.class);
        return bo;
    }

    @Override
    public List<PositionBO> findChild(String id) throws SerException {
        PositionDTO dto = new PositionDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Position> list = super.findByCis(dto, false);
        return BeanTransform.copyProperties(list, PositionBO.class);
    }
}
