package com.bjike.goddess.moneyprepare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyprepare.bo.ProportionBO;
import com.bjike.goddess.moneyprepare.dto.ProportionDTO;
import com.bjike.goddess.moneyprepare.service.ProportionSer;
import com.bjike.goddess.moneyprepare.to.GuidePermissionTO;
import com.bjike.goddess.moneyprepare.to.ProportionObjectTO;
import com.bjike.goddess.moneyprepare.to.ProportionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 比例分配业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 11:59 ]
 * @Description: [ 比例分配业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("proportionApiImpl")
public class ProportionApiImpl implements ProportionAPI {
    @Autowired
    private ProportionSer proportionSer;


    @Override
    public Long countProportion(ProportionDTO proportionDTO) throws SerException {
        return proportionSer.countProportion(proportionDTO);
    }

    @Override
    public ProportionBO getOneById(String id) throws SerException {
        return proportionSer.getOneById(id);
    }

    @Override
    public List<ProportionBO> listProportion(ProportionDTO proportionDTO) throws SerException {
        return proportionSer.listProportion(proportionDTO);
    }

    @Override
    public List<ProportionBO> addProportion(ProportionObjectTO proportionObjectTO) throws SerException {
        return proportionSer.addProportion(proportionObjectTO);
    }

    @Override
    public ProportionBO editProportion(ProportionTO proportionTO) throws SerException {
        return proportionSer.editProportion(proportionTO);
    }

    @Override
    public void deleteProportion(String id) throws SerException {
        proportionSer.deleteProportion(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return proportionSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return proportionSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ProportionBO> editDetail(ProportionTO proportionTO) throws SerException {
        return proportionSer.editDetail(proportionTO);
    }
}