package com.bjike.goddess.businessevaluate.api;

import com.bjike.goddess.businessevaluate.bo.BusinessEvaluateCollectBO;
import com.bjike.goddess.businessevaluate.bo.EvaluateCollectTotalBO;
import com.bjike.goddess.businessevaluate.dto.BusinessEvaluateCollectDTO;
import com.bjike.goddess.businessevaluate.service.BusinessEvaluateCollectSer;
import com.bjike.goddess.businessevaluate.to.BusinessEvaluateCollectTO;
import com.bjike.goddess.businessevaluate.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务评估汇总业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [商务评估汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("businessEvaluateCollectApiImpl")
public class BusinessEvaluateCollectApiImpl implements BusinessEvaluateCollectAPI {

    @Autowired
    private BusinessEvaluateCollectSer businessEvaluateCollectSer;

    @Override
    public BusinessEvaluateCollectBO addModel(BusinessEvaluateCollectTO to) throws SerException {
        return businessEvaluateCollectSer.insertModel(to);
    }

    @Override
    public BusinessEvaluateCollectBO editModel(BusinessEvaluateCollectTO to) throws SerException {
        return businessEvaluateCollectSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        businessEvaluateCollectSer.remove(id);
    }

    @Override
    public void freeze(String id) throws SerException {
        businessEvaluateCollectSer.freeze(id);
    }

    @Override
    public void breakFreeze(String id) throws SerException {
        businessEvaluateCollectSer.breakFreeze(id);
    }

    @Override
    public List<BusinessEvaluateCollectBO> pageList(BusinessEvaluateCollectDTO dto) throws SerException {
        return businessEvaluateCollectSer.pageList(dto);
    }

    @Override
    public List<EvaluateCollectTotalBO> collectionTotal(String area, String project) throws SerException {
        return businessEvaluateCollectSer.collectTotal(area, project);
    }

    @Override
    public BusinessEvaluateCollectBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(businessEvaluateCollectSer.findById(id),BusinessEvaluateCollectBO.class);
    }

    @Override
    public Long count(BusinessEvaluateCollectDTO dto) throws SerException {
        return businessEvaluateCollectSer.count(dto);
    }

    @Override
    public void sendCollectEmail() throws SerException {
        businessEvaluateCollectSer.sendCollectEmail();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        return businessEvaluateCollectSer.guidePermission(to);
    }
}