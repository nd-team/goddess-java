package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.BuyscheduleBO;
import com.bjike.goddess.staffshares.bo.BuyscheduleCollectBO;
import com.bjike.goddess.staffshares.dto.BuyscheduleDTO;
import com.bjike.goddess.staffshares.service.BuyscheduleSer;
import com.bjike.goddess.staffshares.to.BuyscheduleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 买入记录表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:09 ]
 * @Description: [ 买入记录表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("buyscheduleApiImpl")
public class BuyscheduleApiImpl implements BuyscheduleAPI {
    @Autowired
    private BuyscheduleSer buyscheduleSer;

    @Override
    public List<BuyscheduleBO> maps(BuyscheduleDTO dto) throws SerException {
        return buyscheduleSer.maps(dto);
    }

    @Override
    public BuyscheduleBO getById(String id) throws SerException {
        return buyscheduleSer.getById(id);
    }

    @Override
    public Long getTotal(BuyscheduleDTO buyscheduleDTO) throws SerException {
        return buyscheduleSer.getTotal(buyscheduleDTO);
    }

    @Override
    public void sell(BuyscheduleTO buyscheduleTO) throws SerException {
        buyscheduleSer.sell(buyscheduleTO);
    }

    @Override
    public List<BuyscheduleCollectBO> collect() throws SerException {
        return buyscheduleSer.collect();
    }
}