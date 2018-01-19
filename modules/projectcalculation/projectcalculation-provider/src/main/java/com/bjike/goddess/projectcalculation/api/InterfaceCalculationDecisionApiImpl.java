package com.bjike.goddess.projectcalculation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcalculation.bo.InterfaceCalculationDecisionBO;
import com.bjike.goddess.projectcalculation.service.InterfaceCalculationDecisionSer;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDecisionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 界面测算决策业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-09 04:23 ]
 * @Description: [ 界面测算决策业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("interfaceCalculationDecisionApiImpl")
public class InterfaceCalculationDecisionApiImpl implements InterfaceCalculationDecisionAPI {

    @Autowired
    private InterfaceCalculationDecisionSer interfaceCalculationDecisionSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return interfaceCalculationDecisionSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<InterfaceCalculationDecisionBO> getList() throws SerException {
        return interfaceCalculationDecisionSer.getList();
    }

    @Override
    public List<InterfaceCalculationDecisionBO> searchByArea(String area) throws SerException {
        return interfaceCalculationDecisionSer.searchByArea(area);
    }

    @Override
    public List<InterfaceCalculationDecisionBO> searchByProjectNum(String projectnum) throws SerException {
        return interfaceCalculationDecisionSer.searchByProjectNum(projectnum);
    }

    @Override
    public void save(InterfaceCalculationDecisionTO to) throws SerException {
        interfaceCalculationDecisionSer.save(to);
    }

    @Override
    public InterfaceCalculationDecisionBO editor(String id) throws SerException {
        return interfaceCalculationDecisionSer.editor(id);
    }

    @Override
    public void upDate(InterfaceCalculationDecisionTO to) throws SerException {
        interfaceCalculationDecisionSer.upDate(to);
    }

    @Override
    public void submit(InterfaceCalculationDecisionTO to) {

    }
}