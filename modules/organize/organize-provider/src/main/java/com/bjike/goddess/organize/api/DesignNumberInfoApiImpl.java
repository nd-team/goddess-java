package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.dto.DesignNumberInfoDTO;
import com.bjike.goddess.organize.service.DesignNumberInfoSer;
import com.bjike.goddess.organize.to.DesignNumberInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 编号设计信息业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:26]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("designNumberInfoApiImpl")

public class DesignNumberInfoApiImpl implements DesignNumberInfoAPI {

    @Autowired
    private DesignNumberInfoSer designNumberInfoSer;

    @Override
    public DesignNumberInfoBO save(DesignNumberInfoTO to) throws SerException {
        return designNumberInfoSer.save(to);
    }

    @Override
    public DesignNumberInfoBO update(DesignNumberInfoTO to) throws SerException {
        return designNumberInfoSer.update(to);
    }

    @Override
    public DesignNumberInfoBO delete(String id) throws SerException {
        return designNumberInfoSer.delete(id);
    }

    @Override
    public List<DesignNumberInfoBO> maps(DesignNumberInfoDTO dto) throws SerException {
        return designNumberInfoSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        DesignNumberInfoDTO dto = new DesignNumberInfoDTO();
        return designNumberInfoSer.count(dto);
    }

    @Override
    public DesignNumberInfoBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(designNumberInfoSer.findById(id), DesignNumberInfoBO.class);
    }
}
