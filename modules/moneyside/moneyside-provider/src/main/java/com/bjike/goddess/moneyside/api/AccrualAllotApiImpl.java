package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.AccrualAllotBO;
import com.bjike.goddess.moneyside.dto.AccrualAllotDTO;
import com.bjike.goddess.moneyside.service.AccrualAllotSer;
import com.bjike.goddess.moneyside.to.AccrualAllotTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权责分配业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:13 ]
 * @Description: [ 权责分配业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accrualAllotApiImpl")
public class AccrualAllotApiImpl implements AccrualAllotAPI {

    @Autowired
    private AccrualAllotSer accrualAllotSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return accrualAllotSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return accrualAllotSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countAccrualAllot(AccrualAllotDTO accrualAllotDTO) throws SerException {
        return accrualAllotSer.countAccrualAllot(accrualAllotDTO);
    }

    @Override
    public AccrualAllotBO getOne(String id) throws SerException {
        return accrualAllotSer.getOne(id);
    }

    @Override
    public List<AccrualAllotBO> findListAccrualAllot(AccrualAllotDTO accrualAllotDTO) throws SerException {
        return accrualAllotSer.findListAccrualAllot(accrualAllotDTO);
    }

    @Override
    public AccrualAllotBO insertAccrualAllot(AccrualAllotTO accrualAllotTO) throws SerException {
        return accrualAllotSer.insertAccrualAllot(accrualAllotTO);
    }

    @Override
    public AccrualAllotBO editAccrualAllot(AccrualAllotTO accrualAllotTO) throws SerException {
        return accrualAllotSer.editAccrualAllot(accrualAllotTO);
    }

    @Override
    public void removeAccrualAllot(String id) throws SerException {
        accrualAllotSer.removeAccrualAllot(id);
    }

}