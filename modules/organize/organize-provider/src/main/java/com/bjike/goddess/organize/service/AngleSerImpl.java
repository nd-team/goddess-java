package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.AngleBO;
import com.bjike.goddess.organize.dto.AngleDTO;
import com.bjike.goddess.organize.entity.Angle;
import com.bjike.goddess.organize.to.AngleTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角度业务实现
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class AngleSerImpl extends ServiceImpl<Angle, AngleDTO> implements AngleSer {

    @Override
    public List<AngleBO> findStatus() throws SerException {
        AngleDTO dto = new AngleDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Angle> angleList = super.findByCis(dto);
        List<AngleBO> angleBOList = BeanTransform.copyProperties(angleList, AngleBO.class);
        return angleBOList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AngleBO saveAsTo(AngleTO to) throws SerException {
        Angle angle = BeanTransform.copyProperties(to, Angle.class, true);
        super.save(angle);
        return BeanTransform.copyProperties(angle, AngleBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateAsTo(AngleTO to) throws SerException {
        Angle angle = super.findById(to.getId());
        angle.setName(to.getName());
        angle.setDescription(to.getDescription());
        super.update(angle);
    }

}
