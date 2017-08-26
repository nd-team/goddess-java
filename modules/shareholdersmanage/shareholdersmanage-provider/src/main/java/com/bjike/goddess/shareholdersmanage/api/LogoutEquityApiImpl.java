package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.LogoutEquityBO;
import com.bjike.goddess.shareholdersmanage.dto.LogoutEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.LogoutEquity;
import com.bjike.goddess.shareholdersmanage.service.LogoutEquitySer;
import com.bjike.goddess.shareholdersmanage.to.LogoutEquityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注销股权业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:51 ]
 * @Description: [ 注销股权业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("logoutEquityApiImpl")
public class LogoutEquityApiImpl implements LogoutEquityAPI {
    @Autowired
    private LogoutEquitySer logoutEquitySer;
    @Override
    public Long countLogEquity(LogoutEquityDTO logoutEquityDTO) throws SerException {
        return logoutEquitySer.countLogEquity(logoutEquityDTO);
    }

    @Override
    public LogoutEquityBO getOne(String id) throws SerException {
        return logoutEquitySer.getOne(id);
    }

    @Override
    public List<LogoutEquityBO> findList(LogoutEquityDTO logoutEquityDTO) throws SerException {
        return logoutEquitySer.findList(logoutEquityDTO);
    }

    @Override
    public LogoutEquityBO save(LogoutEquityTO logoutEquityTO) throws SerException {
        return logoutEquitySer.save(logoutEquityTO);
    }

    @Override
    public LogoutEquityBO edit(LogoutEquityTO logoutEquityTO) throws SerException {
        return logoutEquitySer.edit(logoutEquityTO);
    }

    @Override
    public void delete(String id) throws SerException {
        logoutEquitySer.delete(id);
    }
}