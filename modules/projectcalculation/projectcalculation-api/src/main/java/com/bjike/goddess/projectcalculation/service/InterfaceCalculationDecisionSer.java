package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectcalculation.bo.InterfaceCalculationDecisionBO;
import com.bjike.goddess.projectcalculation.dto.InterfaceCalculationDecisionDTO;
import com.bjike.goddess.projectcalculation.entity.InterfaceCalculationDecision;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDecisionTO;

import java.util.List;

/**
 * 界面测算决策业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-09 04:23 ]
 * @Description: [ 界面测算决策业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InterfaceCalculationDecisionSer extends Ser<InterfaceCalculationDecision, InterfaceCalculationDecisionDTO> {

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    List<InterfaceCalculationDecisionBO> getList() throws SerException;

    List<InterfaceCalculationDecisionBO> searchByArea(String area) throws SerException;

    List<InterfaceCalculationDecisionBO> searchByProjectNum(String projectnum) throws SerException;

    void save(InterfaceCalculationDecisionTO to) throws SerException;

    InterfaceCalculationDecisionBO editor(String id) throws SerException;

    void upDate(InterfaceCalculationDecisionTO to) throws SerException;

    void submit(InterfaceCalculationDecisionTO to);

}