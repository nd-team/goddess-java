package com.bjike.goddess.checkfunds.api;

import com.bjike.goddess.checkfunds.bo.RemainAdjustBO;
import com.bjike.goddess.checkfunds.dto.RemainAdjustDTO;
import com.bjike.goddess.checkfunds.service.RemainAdjustSer;
import com.bjike.goddess.checkfunds.to.RemainAdjustTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 余额调节业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 04:11 ]
 * @Description: [ 余额调节业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("remainAdjustApiImpl")
public class RemainAdjustApiImpl implements RemainAdjustAPI {
    @Autowired
    private RemainAdjustSer remainAdjustSer;

    @Override
    public void save(RemainAdjustTO to) throws SerException {
        remainAdjustSer.save(to);
    }

    @Override
    public List<RemainAdjustBO> findByDTO(RemainAdjustDTO dto) throws SerException {
        return remainAdjustSer.findByDTO(dto);
    }

    @Override
    public List<RemainAdjustBO> addFund(RemainAdjustTO to, String id) throws SerException {
        return remainAdjustSer.addFund(to, id);
    }

    @Override
    public List<RemainAdjustBO> removeFund(RemainAdjustTO to, String id) throws SerException {
        return remainAdjustSer.removeFund(to, id);
    }

    @Override
    public List<RemainAdjustBO> addBank(RemainAdjustTO to, String id) throws SerException {
        return remainAdjustSer.addBank(to, id);
    }

    @Override
    public List<RemainAdjustBO> removeBank(RemainAdjustTO to, String id) throws SerException {
        return remainAdjustSer.removeBank(to, id);
    }

    @Override
    public void confirmAdjust(String id, Double fundBalance, Double bankBalance) throws SerException {
        remainAdjustSer.confirmAdjust(id, fundBalance, bankBalance);
    }

    @Override
    public Long countNum(RemainAdjustDTO dto) throws SerException {
        return remainAdjustSer.countNum(dto);
    }
}