package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.AngleBO;
import com.bjike.goddess.organize.dto.AngleDTO;
import com.bjike.goddess.organize.service.AngleSer;
import com.bjike.goddess.organize.to.AngleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("angleApiImpl")
public class AngleApiImpl implements AngleAPI {

    @Autowired
    private AngleSer angleSer;

    @Override
    public List<AngleBO> findStatus() throws SerException {
        return angleSer.findStatus();
    }

    @Override
    public AngleBO saveAsTo(AngleTO to) throws SerException {
        return angleSer.saveAsTo(to);
    }

    @Override
    public void updateAsTo(AngleTO to) throws SerException {
        angleSer.updateAsTo(to);
    }

    @Override
    public AngleBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(angleSer.findById(id), AngleBO.class);
    }

    @Override
    public AngleBO findOne(AngleDTO dto) throws SerException {
        return BeanTransform.copyProperties(angleSer.findOne(dto), AngleBO.class);
    }

    @Override
    public List<AngleBO> findByCis(AngleDTO dto) throws SerException {
        return BeanTransform.copyProperties(angleSer.findByCis(dto, false), AngleBO.class);
    }

    @Override
    public AngleBO delete(String id) throws SerException {
        return angleSer.delete(id);
    }

    @Override
    public AngleBO close(String id) throws SerException {
        return angleSer.close(id);
    }

    @Override
    public AngleBO open(String id) throws SerException {
        return angleSer.delete(id);
    }

    @Override
    public List<AngleBO> maps(AngleDTO dto) throws SerException {
        return angleSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        AngleDTO dto = new AngleDTO();
        return angleSer.count(dto);
    }
}
