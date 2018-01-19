package com.bjike.goddess.projectcalculation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcalculation.bo.CalculationDecisionsBO;
import com.bjike.goddess.projectcalculation.service.CalculationDecisionsSer;
import com.bjike.goddess.projectcalculation.to.CalculationDecisionsTO;
import com.bjike.goddess.projectcalculation.to.CalculationDetailTO;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测算决策业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-07 02:41 ]
 * @Description: [ 测算决策业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("calculationDecisionsApiImpl")
public class CalculationDecisionsApiImpl implements CalculationDecisionsAPI {
    @Autowired
    private CalculationDecisionsSer calculationDecisionsSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return calculationDecisionsSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<CalculationDecisionsBO> getList() throws SerException {
        return calculationDecisionsSer.getList();
    }

    @Override
    public void save(CalculationDecisionsTO calculationDecisionsTO) throws SerException {
        calculationDecisionsSer.save(calculationDecisionsTO);
    }

    @Override
    public List<CalculationDecisionsBO> getSearchList(String marketInfoNum) throws SerException {
        return calculationDecisionsSer.getSearchList(marketInfoNum);
    }

    @Override
    public CalculationDecisionsBO getProjectManage(CalculationDecisionsTO to) throws SerException {
        calculationDecisionsSer.getProjectManage(to);
        return null;
    }

    @Override
    public void upDate(CalculationDecisionsTO to) throws SerException {
        calculationDecisionsSer.upDate(to);
    }
}