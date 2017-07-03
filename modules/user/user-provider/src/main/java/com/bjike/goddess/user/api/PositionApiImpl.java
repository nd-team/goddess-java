package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.dto.PositionDTO;
import com.bjike.goddess.user.service.PositionSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对外提供岗位业务实现
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-16 11:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("positionApiImpl")
public class PositionApiImpl implements PositionAPI {

    @Autowired
    private PositionSer positionSer;

    @Override
    public PositionBO findById(String id) throws SerException {
        return positionSer.findBOById(id);
    }

    @Override
    public PositionBO findParent(String id) throws SerException {
        return positionSer.findParent(id);
    }

    @Override
    public List<PositionBO> findChild(String id) throws SerException {
        return positionSer.findChild(id);
    }

    @Override
    public List<PositionBO> findByCis(PositionDTO dto) throws SerException {
        return BeanTransform.copyProperties(positionSer.findByCis(dto), PositionBO.class);
    }
}
