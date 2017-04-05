package com.bjike.goddess.businessevaluate.api.interiorevaluate;

import com.bjike.goddess.businessevaluate.bo.interiorevaluate.FrontlineEvaluateBO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.FrontlineEvaluateDTO;
import com.bjike.goddess.businessevaluate.service.interiorevaluate.FrontlineEvaluateSer;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.FrontlineEvaluateTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 一线体系评价业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:00 ]
 * @Description: [ 一线体系评价业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("frontlineEvaluateApiImpl")
public class FrontlineEvaluateApiImpl implements FrontlineEvaluateAPI {

    @Autowired
    private FrontlineEvaluateSer frontlineEvaluateSer;

    @Override
    public FrontlineEvaluateBO addModel(FrontlineEvaluateTO to) throws SerException {
        return frontlineEvaluateSer.insertModel(to);
    }

    @Override
    public FrontlineEvaluateBO editModel(FrontlineEvaluateTO to) throws SerException {
        return frontlineEvaluateSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        frontlineEvaluateSer.remove(id);
    }

    @Override
    public List<FrontlineEvaluateBO> pageList(FrontlineEvaluateDTO dto) throws SerException {
        return frontlineEvaluateSer.pageList(dto);
    }
}