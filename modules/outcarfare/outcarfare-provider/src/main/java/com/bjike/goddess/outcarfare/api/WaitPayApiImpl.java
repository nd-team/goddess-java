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
import java.util.Set;

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

    @Override
    public List<WaitPayBO> delList(WaitPayDTO dto) throws SerException {
        return waitPaySer.delList(dto);
    }

    @Override
    public void reback(String id) throws SerException {
        waitPaySer.reback(id);
    }

    @Override
    public Long delCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.delCount(dto);
    }

    @Override
    public void quartz() throws SerException {
        waitPaySer.quartz();
    }

    @Override
    public List<DriverCountBO> driverCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.driverCount(dto);
    }

    @Override
    public List<ArrivalCountBO> arrivalCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.arrivalCount(dto);
    }

    @Override
    public List<CarUserCountBO> carUserCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.carUserCount(dto);
    }

    @Override
    public List<DriverCountBO> waitDriverCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitDriverCount(dto);
    }

    @Override
    public List<ArrivalCountBO> waitArrivalCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitArrivalCount(dto);
    }

    @Override
    public List<CarUserCountBO> waitCarUserCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitCarUserCount(dto);
    }

    @Override
    public List<WaitPayBO> waitDetails(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitDetails(dto);
    }

    @Override
    public List<WaitPayBO> details(WaitPayDTO dto) throws SerException {
        return waitPaySer.details(dto);
    }

    @Override
    public Set<String> findAllDrivers() throws SerException {
        return waitPaySer.findAllDrivers();
    }

    @Override
    public Set<String> findAllArrivals() throws SerException {
        return waitPaySer.findAllArrivals();
    }

    @Override
    public Set<String> findAllCarUsers() throws SerException {
        return waitPaySer.findAllCarUsers();
    }
}