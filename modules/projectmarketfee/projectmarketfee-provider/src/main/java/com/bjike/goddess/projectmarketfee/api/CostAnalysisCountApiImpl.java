package com.bjike.goddess.projectmarketfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisCountBO;
import com.bjike.goddess.projectmarketfee.service.CostAnalysisCountSer;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisCountTO;
import com.bjike.goddess.projectmarketfee.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 费用效益分析业务汇总业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 03:13 ]
 * @Description: [ 费用效益分析业务汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("costAnalysisCountApiImpl")
public class CostAnalysisCountApiImpl implements CostAnalysisCountAPI {
    @Autowired
    private CostAnalysisCountSer costAnalysisCountSer;

    @Override
    public CostAnalysisCountBO save(CostAnalysisCountTO to) throws SerException {
        return costAnalysisCountSer.save(to);
    }

    @Override
    public CostAnalysisCountBO findByID(String id) throws SerException {
        return costAnalysisCountSer.findByID(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return costAnalysisCountSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return costAnalysisCountSer.guidePermission(guidePermissionTO);
    }
}