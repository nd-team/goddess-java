package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.bo.PayStockBO;
import com.bjike.goddess.fundcheck.dto.PayStockDTO;
import com.bjike.goddess.fundcheck.service.PayStockSer;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.PayStockCollectTO;
import com.bjike.goddess.fundcheck.to.PayStockTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 支付给股东业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:55 ]
 * @Description: [ 支付给股东业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("payStockApiImpl")
public class PayStockApiImpl implements PayStockAPI {
    @Autowired
    private PayStockSer payStockSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return payStockSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return payStockSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long count(PayStockDTO payStockDTO) throws SerException {
        return payStockSer.count(payStockDTO);
    }

    @Override
    public PayStockBO getOne(String id) throws SerException {
        return payStockSer.getOne(id);
    }

    @Override
    public List<PayStockBO> findListBack(PayStockDTO payStockDTO) throws SerException {
        return payStockSer.findListBack(payStockDTO);
    }

    @Override
    public PayStockBO insert(PayStockTO payStockTO) throws SerException {
        return payStockSer.insert(payStockTO);
    }

    @Override
    public PayStockBO edit(PayStockTO payStockTO) throws SerException {
        return payStockSer.edit(payStockTO);
    }

    @Override
    public void remove(String id) throws SerException {
        payStockSer.remove(id);
    }
    @Override
    public List<PayStockBO> collect(PayStockCollectTO to) throws SerException {
        return payStockSer.collect(to);
    }
}