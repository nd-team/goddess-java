package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.EvaluateProjectInfoBO;
import com.bjike.goddess.businessevaluate.bo.ProjectProfitRateBO;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.service.EvaluateProjectInfoSer;
import com.bjike.goddess.businessevaluate.to.EvaluateProjectInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目基本信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("evaluateProjectInfoApiImpl")
public class EvaluateProjectInfoApiImpl implements EvaluateProjectInfoAPI {

    @Autowired
    private EvaluateProjectInfoSer evaluateProjectInfoSer;

    @Override
    public EvaluateProjectInfoBO addModel(EvaluateProjectInfoTO to) throws SerException {
        return evaluateProjectInfoSer.addModel(to);
    }

    @Override
    public EvaluateProjectInfoBO editModel(EvaluateProjectInfoTO to) throws SerException {
        return evaluateProjectInfoSer.editModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        evaluateProjectInfoSer.remove(id);
    }

    @Override
    public List<EvaluateProjectInfoBO> pageList(EvaluateProjectInfoDTO dto) throws SerException {
        return evaluateProjectInfoSer.pageList(dto);
    }

    @Override
    public List<ProjectProfitRateBO> profitPageList(EvaluateProjectInfoDTO dto) throws SerException {
        return evaluateProjectInfoSer.profitPageList(dto);
    }

    @Override
    public List<ProjectProfitRateBO> profitScope() throws SerException {
        return evaluateProjectInfoSer.profitScope();
    }

    @Override
    public List<EvaluateProjectInfoBO> findAll() throws SerException {
        return BeanTransform.copyProperties(evaluateProjectInfoSer.findAll(), EvaluateProjectInfoBO.class);
    }

    @Override
    public Long count(EvaluateProjectInfoDTO dto) throws SerException {
        return evaluateProjectInfoSer.count(dto);
    }

    @Override
    public EvaluateProjectInfoBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(evaluateProjectInfoSer.findById(id),EvaluateProjectInfoBO.class);
    }
}