package com.bjike.goddess.contractcommunicat.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.BNCollectEchartBO;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationCollectBO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationCollectDTO;
import com.bjike.goddess.contractcommunicat.enums.CollectTimeType;
import com.bjike.goddess.contractcommunicat.excel.SonPermissionObject;
import com.bjike.goddess.contractcommunicat.service.BusinessNegotiationCollectSer;
import com.bjike.goddess.contractcommunicat.service.BusinessNegotiationSer;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 洽谈汇总实现
 * @Author: [caiwenxian]
 * @Date: [2018-01-03 10:48]
 * @Description: [ 洽谈汇总实现　]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("businessNegotiationCollectApiImpl")
public class BusinessNegotiationCollectApiImpl implements BusinessNegotiationCollectAPI {

    @Autowired
    private BusinessNegotiationSer businessNegotiationSer;
    @Autowired
    BusinessNegotiationCollectSer businessNegotiationCollectSerl;


    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return businessNegotiationSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(BusinessNegotiationCollectDTO dto) throws SerException {
        return businessNegotiationCollectSerl.count(dto);
    }

    @Override
    public List<BusinessNegotiationCollectBO> listBusinessNegotiationCollect(BusinessNegotiationCollectDTO dto, CollectTimeType type, String... params) throws SerException {
        return businessNegotiationCollectSerl.listBusinessNegotiationCollect(dto, type, params);
    }

    @Override
    public BNCollectEchartBO listCollectEchart(CollectTimeType type, String... params) throws SerException {
        return businessNegotiationCollectSerl.listCollectEchart(type, params);
    }

    @Override
    public BNCollectEchartBO listCollectEchartByType(String businessType, CollectTimeType type, String... params) throws SerException {
        return businessNegotiationCollectSerl.listCollectEchartByType(businessType, type, params);
    }

    @Override
    public Set<String> listBusinessType() throws SerException {
        return businessNegotiationCollectSerl.listBusinessType();
    }
}
