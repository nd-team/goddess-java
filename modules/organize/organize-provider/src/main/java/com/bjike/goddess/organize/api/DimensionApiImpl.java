package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.DimensionBO;
import com.bjike.goddess.organize.dto.DimensionDTO;
import com.bjike.goddess.organize.service.DimensionSer;
import com.bjike.goddess.organize.to.DimensionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("dimensionApiImpl")

public class DimensionApiImpl implements DimensionAPI {

    @Autowired
    private DimensionSer dimensionSer;

    @Override
    public List<DimensionBO> findStatus() throws SerException {
        return dimensionSer.findStatus();
    }

    @Override
    public DimensionBO save(DimensionTO to) throws SerException {
        return dimensionSer.save(to);
    }

    @Override
    public DimensionBO update(DimensionTO to) throws SerException {
        return dimensionSer.update(to);
    }

    @Override
    public DimensionBO delete(String id) throws SerException {
        return dimensionSer.delete(id);
    }

    @Override
    public DimensionBO close(String id) throws SerException {
        return dimensionSer.close(id);
    }

    @Override
    public DimensionBO open(String id) throws SerException {
        return dimensionSer.open(id);
    }

    @Override
    public List<DimensionBO> maps(DimensionDTO dto) throws SerException {
        return dimensionSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        DimensionDTO dto = new DimensionDTO();
        return dimensionSer.count(dto);
    }

    @Override
    public DimensionBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(dimensionSer.findById(id), DimensionBO.class);
    }
}
