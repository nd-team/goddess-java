package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.FunPowerWeightFactorBO;
import com.bjike.goddess.customer.dto.FunPowerWeightFactorDTO;
import com.bjike.goddess.customer.service.FunPowerWeightFactorSer;
import com.bjike.goddess.customer.service.FunPowerWeightFactorSerImpl;
import com.bjike.goddess.customer.to.FunPowerWeightFactorTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职权因素层设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:37 ]
 * @Description: [ 职权因素层设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("funPowerWeightFactorApiImpl")
public class FunPowerWeightFactorApiImpl implements FunPowerWeightFactorAPI {
    @Autowired
    private FunPowerWeightFactorSer funPowerWeightFactorSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return funPowerWeightFactorSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return funPowerWeightFactorSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countFunPower(FunPowerWeightFactorDTO funPowerWeightFactorDTO) throws SerException {
        return funPowerWeightFactorSer.countFunPower(funPowerWeightFactorDTO);
    }

    @Override
    public FunPowerWeightFactorBO getOneFunPower(String id) throws SerException {
        return funPowerWeightFactorSer.getOneFunPower(id);
    }

    @Override
    public List<FunPowerWeightFactorBO> listFunPower(FunPowerWeightFactorDTO funPowerWeightFactorDTO) throws SerException {
        return funPowerWeightFactorSer.listFunPower(funPowerWeightFactorDTO);
    }

    @Override
    public FunPowerWeightFactorBO addFunPower(FunPowerWeightFactorTO funPowerWeightFactorTO) throws SerException {
        return funPowerWeightFactorSer.addFunPower(funPowerWeightFactorTO);
    }

    @Override
    public FunPowerWeightFactorBO editFunPower(FunPowerWeightFactorTO funPowerWeightFactorTO) throws SerException {
        return funPowerWeightFactorSer.editFunPower(funPowerWeightFactorTO);
    }

    @Override
    public void deleteFunPower(String id) throws SerException {
        funPowerWeightFactorSer.deleteFunPower(id);
    }
}