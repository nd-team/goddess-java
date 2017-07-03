package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.BuyBO;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.service.BuySer;
import com.bjike.goddess.secure.to.BuyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购买社保人员业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("buyApiImpl")
public class BuyApiImpl implements BuyAPI {
    @Autowired
    private BuySer buySer;

    @Override
    public List<BuyBO> find(BuyDTO dto) throws SerException {
        return buySer.find(dto);
    }

    @Override
    public BuyBO edit(BuyTO to) throws SerException {
        return buySer.edit(to);
    }

    @Override
    public BuyBO delete(String id) throws SerException {
        return buySer.delete(id);
    }

    @Override
    public BuyBO findByID(String id) throws SerException {
        return buySer.findByID(id);
    }

    @Override
    public BuyBO save(BuyTO to) throws SerException {
        return buySer.save(to);
    }

    @Override
    public Long count(BuyDTO dto) throws SerException {
        return buySer.count(dto);
    }
}