package com.bjike.goddess.outcarfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.outcarfare.bo.*;
import com.bjike.goddess.outcarfare.dto.WaitPayDTO;
import com.bjike.goddess.outcarfare.service.WaitPaySer;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.WaitPayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 03:11 ]
 * @Description: [ 等待付款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("waitPayApiImpl")
public class WaitPayApiImpl implements WaitPayAPI {
    @Autowired
    private WaitPaySer waitPaySer;

    @Override
    public WaitPayBO save(WaitPayTO to) throws SerException {
        return waitPaySer.save(to);
    }

    @Override
    public void pay(WaitPayTO to) throws SerException {
        waitPaySer.pay(to);
    }
    @Override
    public WaitPayBO edit(WaitPayTO to) throws SerException {
        return waitPaySer.edit(to);
    }
    @Override
    public void delete(String id) throws SerException {
        waitPaySer.delete(id);
    }

    @Override
    public List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        return waitPaySer.list(dto);
    }

    @Override
    public WaitPayBO findByID(String id) throws SerException {
        return waitPaySer.findByID(id);
    }

    @Override
    public List<DriverCountBO> driverCount() throws SerException {
        return waitPaySer.driverCount();
    }

    @Override
    public List<ArrivalCountBO> arrivalCount() throws SerException {
        return waitPaySer.arrivalCount();
    }

    @Override
    public List<CarUserCountBO> carUserCount() throws SerException {
        return waitPaySer.carUserCount();
    }

    @Override
    public List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        return waitPaySer.pays(dto);
    }

    @Override
    public Long waitCountSum(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitCountSum(dto);
    }

    @Override
    public Long payCountSum(WaitPayDTO dto) throws SerException {
        return waitPaySer.payCountSum(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return waitPaySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return waitPaySer.guidePermission(guidePermissionTO);
    }
}