package com.bjike.goddess.equipmentprepared.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.equipmentprepared.bo.PayCountBO;
import com.bjike.goddess.equipmentprepared.bo.WaitPayBO;
import com.bjike.goddess.equipmentprepared.dto.WaitPayDTO;
import com.bjike.goddess.equipmentprepared.service.WaitPaySer;
import com.bjike.goddess.equipmentprepared.to.WaitPayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-13 02:27 ]
 * @Description: [ 等待付款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("waitPayApiImpl")
public class WaitPayApiImpl implements WaitPayAPI {
    @Autowired
    private WaitPaySer waitPaySer;

    @Override
    public List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        return waitPaySer.list(dto);
    }

    @Override
    public List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        return waitPaySer.pays(dto);
    }

    @Override
    public List<PayCountBO> departmentCount() throws SerException {
        return waitPaySer.departmentCount();
    }

    @Override
    public List<PayCountBO> areaCount() throws SerException {
        return waitPaySer.areaCount();
    }

    @Override
    public List<PayCountBO> deviceNameCount() throws SerException {
        return waitPaySer.deviceNameCount();
    }

    @Override
    public void confirmPay(WaitPayTO to) throws SerException {
        waitPaySer.confirmPay(to);
    }

    @Override
    public List<WaitPayBO> export(Integer year, Integer month) throws SerException {
        return waitPaySer.export(year, month);
    }

    @Override
    public WaitPayBO findByID(String id) throws SerException {
        return waitPaySer.findByID(id);
    }

    @Override
    public Long waitCountSum(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitCountSum(dto);
    }

    @Override
    public Long payCountSum(WaitPayDTO dto) throws SerException {
        return waitPaySer.payCountSum(dto);
    }
}