package com.bjike.goddess.projectcalculation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcalculation.bo.CalculationDecisionsBO;
import com.bjike.goddess.projectcalculation.to.CalculationDecisionsTO;
import com.bjike.goddess.projectcalculation.to.CalculationDetailTO;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;

import java.util.List;

/**
 * 测算决策业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-07 02:41 ]
 * @Description: [ 测算决策业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CalculationDecisionsAPI {

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    List<CalculationDecisionsBO> getList() throws SerException;

    void save(CalculationDecisionsTO calculationDecisionsTO) throws SerException;

    List<CalculationDecisionsBO> getSearchList(String marketInfoNum) throws SerException;

    CalculationDecisionsBO getProjectManage(CalculationDecisionsTO to) throws SerException;

    void upDate(CalculationDecisionsTO to) throws SerException;
}