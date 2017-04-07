package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.dto.ArrangementDTO;
import com.bjike.goddess.organize.entity.Arrangement;
import com.bjike.goddess.organize.to.ArrangementTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位层级业务实现
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ArrangementSerImpl extends ServiceImpl<Arrangement, ArrangementDTO> implements ArrangementSer {


    @Override
    public List<ArrangementBO> findStatus() throws SerException {
        ArrangementDTO dto = new ArrangementDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Arrangement> list = super.findByCis(dto);
        List<ArrangementBO> bos = BeanTransform.copyProperties(list, ArrangementBO.class);
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArrangementBO save(ArrangementTO to) throws SerException {
        Arrangement arrangement = BeanTransform.copyProperties(to, Arrangement.class);
        arrangement.setCreateTime(LocalDateTime.now());
        arrangement.setStatus(Status.THAW);
        if (StringUtils.isNotBlank(to.getParen_id())) arrangement.setParent(super.findById(to.getParen_id()));
        super.save(arrangement);
        return BeanTransform.copyProperties(arrangement, ArrangementBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ArrangementBO update(ArrangementTO to) throws SerException {
        Arrangement arrangement = super.findById(to.getId());
        if (StringUtils.isNotBlank(to.getParen_id())) arrangement.setParent(super.findById(to.getParen_id()));
        arrangement.setArrangement(to.getArrangement());
        arrangement.setSerialNumber(to.getSerialNumber());
        arrangement.setDescription(to.getDescription());
        super.update(arrangement);
        return BeanTransform.copyProperties(arrangement, ArrangementBO.class);
    }

    @Override
    public List<ArrangementBO> findChild(String id) throws SerException {
        ArrangementDTO dto = new ArrangementDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        return BeanTransform.copyProperties(super.findByCis(dto), ArrangementBO.class);
    }
}
