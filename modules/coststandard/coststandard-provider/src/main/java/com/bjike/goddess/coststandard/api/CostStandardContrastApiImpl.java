package com.bjike.goddess.coststandard.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.coststandard.bo.CostStandardContrastBO;
import com.bjike.goddess.coststandard.dto.CostStandardContrastDTO;
import com.bjike.goddess.coststandard.service.CostStandardContrastSer;
import com.bjike.goddess.coststandard.to.CostStandardContrastTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 费用标准对比业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:15 ]
 * @Description: [ 费用标准对比业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("costStandardContrastApiImpl")
public class CostStandardContrastApiImpl implements CostStandardContrastAPI {

    @Autowired
    private CostStandardContrastSer costStandardContrastSer;

    @Override
    public CostStandardContrastBO save(CostStandardContrastTO to) throws SerException {
        return costStandardContrastSer.save(to);
    }

    @Override
    public CostStandardContrastBO update(CostStandardContrastTO to) throws SerException {
        return costStandardContrastSer.update(to);
    }

    @Override
    public CostStandardContrastBO delete(String id) throws SerException {
        return costStandardContrastSer.delete(id);
    }

    @Override
    public CostStandardContrastBO getById(String id) throws SerException {
        return costStandardContrastSer.getById(id);
    }

    @Override
    public List<CostStandardContrastBO> maps(CostStandardContrastDTO dto) throws SerException {
        return costStandardContrastSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return costStandardContrastSer.getTotal();
    }
}