package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.NewEquityBO;
import com.bjike.goddess.shareholdersmanage.bo.NewEquityLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.NewEquityDTO;
import com.bjike.goddess.shareholdersmanage.service.NewEquitySer;
import com.bjike.goddess.shareholdersmanage.to.NewEquityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新增股权业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:17 ]
 * @Description: [ 新增股权业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("newEquityApiImpl")
public class NewEquityApiImpl implements NewEquityAPI {
    @Autowired
    private NewEquitySer newEquitySer;

    @Override
    public Long countNewEquity(NewEquityDTO newEquityDTO) throws SerException {
        return newEquitySer.countNewEquity(newEquityDTO);
    }

    @Override
    public NewEquityBO getOne(String id) throws SerException {
        return newEquitySer.getOne(id);
    }

    @Override
    public List<NewEquityBO> findList(NewEquityDTO newEquityDTO) throws SerException {
        return newEquitySer.findList(newEquityDTO);
    }

    @Override
    public NewEquityBO save(NewEquityTO newEquityTO) throws SerException {
        return newEquitySer.save(newEquityTO);
    }

    @Override
    public NewEquityBO edit(NewEquityTO newEquityTO) throws SerException {
        return newEquitySer.edit(newEquityTO);
    }

    @Override
    public void delete(String id) throws SerException {
        newEquitySer.delete(id);
    }

    @Override
    public NewEquityLinkDateBO newEqLinkDate(Integer newHoldNum, String shareholderName) throws SerException {
        return newEquitySer.newEqLinkDate(newHoldNum, shareholderName);
    }
}