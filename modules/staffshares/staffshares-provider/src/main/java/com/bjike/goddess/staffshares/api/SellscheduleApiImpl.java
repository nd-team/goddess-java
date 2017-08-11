package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.SellscheduleBO;
import com.bjike.goddess.staffshares.bo.SellscheduleCollectBO;
import com.bjike.goddess.staffshares.bo.TransactionBO;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.service.SellscheduleSer;
import com.bjike.goddess.staffshares.to.SellscheduleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 出售记录表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:15 ]
 * @Description: [ 出售记录表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("sellscheduleApiImpl")
public class SellscheduleApiImpl implements SellscheduleAPI {
    @Autowired
    private SellscheduleSer sellscheduleSer;

    @Override
    public void sell(SellscheduleTO to) throws SerException {
        sellscheduleSer.sell(to);
    }

    @Override
    public List<SellscheduleBO> maps(SellscheduleDTO dto) throws SerException {
        return sellscheduleSer.maps(dto);
    }

    @Override
    public SellscheduleBO getById(String id) throws SerException {
        return sellscheduleSer.getById(id);
    }

    @Override
    public Long getTotal(SellscheduleDTO sellscheduleDTO) throws SerException {
        return sellscheduleSer.getTotal(sellscheduleDTO);
    }

    @Override
    public List<SellscheduleCollectBO> collect() throws SerException {
        return sellscheduleSer.collect();
    }

    @Override
    public List<TransactionBO> transaction() throws SerException {
        return sellscheduleSer.transaction();
    }
}