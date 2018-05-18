package com.bjike.goddess.socialinsurance.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.socialinsurance.bo.SICollectEchartBO;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceVoucherDTO;
import com.bjike.goddess.socialinsurance.excel.SonPermissionObject;
import com.bjike.goddess.socialinsurance.service.SocialInsuranceCollectSer;
import com.bjike.goddess.socialinsurance.to.GuidePermissionTO;
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
    public Boolean sonPermission() throws SerException {
        return socialInsuranceCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return socialInsuranceCollectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<SocialInsuranceCollectBO> personalCollect(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.personalCollect(dto);
    }

    @Override
    public List<SocialInsuranceCollectBO> departmentCollect(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.departmentCollect(dto);
    }

    @Override
    public List<SocialInsuranceCollectBO> voucherCollect(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.voucherCollect(dto);
    }

    @Override
    public List<SocialInsuranceCollectBO> areaCollect(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.areaCollect(dto);
    }

    @Override
    public SICollectEchartBO personalCollectEchart(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.personalCollectEchart(dto);
    }

    @Override
    public SICollectEchartBO departmentCollectEchart(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.departmentCollectEchart(dto);
    }

    @Override
    public SICollectEchartBO areaCollectEchart(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.areaCollectEchart(dto);
    }

    @Override
    public SICollectEchartBO personalCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.personalCollectPieEchart(dto);
    }

    @Override
    public SICollectEchartBO departmentCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.departmentCollectPieEchart(dto);
    }

    @Override
    public SICollectEchartBO areaCollectPieEchart(SocialInsuranceCollectDTO dto) throws SerException {
        return socialInsuranceCollectSer.areaCollectPieEchart(dto);
    }

    @Override
    public void updateSocialInsurancePayStatus(String department, String startDate, String endDate) throws SerException {
        socialInsuranceCollectSer.updateSocialInsurancePayStatus(department, startDate, endDate);
    }


}
