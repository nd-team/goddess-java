package com.bjike.goddess.oilcardprepared.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.oilcardprepared.bo.ContrastBO;
import com.bjike.goddess.oilcardprepared.bo.WaitPayBO;
import com.bjike.goddess.oilcardprepared.dto.WaitPayDTO;
import com.bjike.goddess.oilcardprepared.service.WaitPaySer;
import com.bjike.goddess.oilcardprepared.to.WaitPayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-12 11:05 ]
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
    public List<WaitPayBO> count(String startTime, String endTime) throws SerException {
        return waitPaySer.count(startTime, endTime);
    }

    @Override
    public List<ContrastBO> contrast(Integer month) throws SerException {
        return waitPaySer.contrast(month);
    }

    @Override
    public List<WaitPayBO> allPay() throws SerException {
        return waitPaySer.allPay();
    }

    @Override
    public WaitPayBO findByID(String id) throws SerException {
        return waitPaySer.findByID(id);
    }

    @Override
    public void confirmPay(WaitPayTO to) throws SerException {
        waitPaySer.confirmPay(to);
    }
}