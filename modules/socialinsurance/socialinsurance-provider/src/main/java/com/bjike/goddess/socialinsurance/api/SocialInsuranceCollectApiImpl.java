package com.bjike.goddess.socialinsurance.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.service.SocialInsuranceCollectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 18:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("socialInsuranceCollectApiImpl")
public class SocialInsuranceCollectApiImpl implements SocialInsuranceCollectAPI {

    @Autowired
    SocialInsuranceCollectSer socialInsuranceCollectSer;

    @Override
    public List<SocialInsuranceCollectBO> personalCollect(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.personalCollect(dto);
    }

    @Override
    public List<SocialInsuranceCollectBO> departmentCollect(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.departmentCollect(dto);
    }

    @Override
    public List<SocialInsuranceCollectBO> areaCollect(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.areaCollect(dto);
    }
}
