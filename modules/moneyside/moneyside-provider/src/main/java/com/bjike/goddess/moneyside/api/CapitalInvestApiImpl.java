package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CapitalInvestBO;
import com.bjike.goddess.moneyside.dto.CapitalInvestDTO;
import com.bjike.goddess.moneyside.service.CapitalInvestSer;
import com.bjike.goddess.moneyside.to.CapitalInvestTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金投资业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:00 ]
 * @Description: [ 资金投资业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("capitalInvestApiImpl")
public class CapitalInvestApiImpl implements CapitalInvestAPI {
    @Autowired
    private CapitalInvestSer capitalInvestSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return capitalInvestSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return capitalInvestSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countCapitalInvest(CapitalInvestDTO capitalInvestDTO) throws SerException {
        return capitalInvestSer.countCapitalInvest(capitalInvestDTO);
    }

    @Override
    public CapitalInvestBO getOne(String id) throws SerException {
        return capitalInvestSer.getOne(id);
    }

    @Override
    public List<CapitalInvestBO> findListCapitalInvest(CapitalInvestDTO capitalInvestDTO) throws SerException {
        return capitalInvestSer.findListCapitalInvest(capitalInvestDTO);
    }

    @Override
    public CapitalInvestBO insertCapitalInvest(CapitalInvestTO capitalInvestTO) throws SerException {
        return capitalInvestSer.insertCapitalInvest(capitalInvestTO);
    }

    @Override
    public CapitalInvestBO editCapitalInvest(CapitalInvestTO capitalInvestTO) throws SerException {
        return capitalInvestSer.editCapitalInvest(capitalInvestTO);
    }

    @Override
    public void removeCapitalInvest(String id) throws SerException {
        capitalInvestSer.removeCapitalInvest(id);
    }
    @Override
    public CapitalInvestBO getInnerProject(String innerProject) throws SerException {
        return capitalInvestSer.getInnerProject(innerProject);
    }
    @Override
    public List<String> getInnerProject() throws SerException {
        return capitalInvestSer.getInnerProject();
    }
}