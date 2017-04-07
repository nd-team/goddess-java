package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.LabourCostBO;
import com.bjike.goddess.businessevaluate.dto.LabourCostDTO;
import com.bjike.goddess.businessevaluate.service.LabourCostSer;
import com.bjike.goddess.businessevaluate.to.LabourCostTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目基本信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 09:16 ]
 * @Description: [ 项目基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("labourCostApiImpl")
public class LabourCostApiImpl implements LabourCostAPI {

    @Autowired
    private LabourCostSer labourCostSer;

    @Override
    public LabourCostBO addModel(LabourCostTO to) throws SerException {
        return labourCostSer.insertModel(to);
    }

    @Override
    public LabourCostBO editModel(LabourCostTO to) throws SerException {
        return labourCostSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        labourCostSer.remove(id);
    }

    @Override
    public List<LabourCostBO> pageList(LabourCostDTO dto) throws SerException {
        return labourCostSer.pageList(dto);
    }
}