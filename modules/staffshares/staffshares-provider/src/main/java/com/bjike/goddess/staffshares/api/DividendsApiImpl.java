package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.CompanySchemeBO;
import com.bjike.goddess.staffshares.bo.DividendsBO;
import com.bjike.goddess.staffshares.dto.DividendsDTO;
import com.bjike.goddess.staffshares.service.DividendsSer;
import com.bjike.goddess.staffshares.to.DividendsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 干股分红表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 干股分红表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dividendsApiImpl")
public class DividendsApiImpl implements DividendsAPI {
    @Autowired
    private DividendsSer dividendsSer;

    @Override
    public List<CompanySchemeBO> detail() throws SerException {
        return dividendsSer.detail();
    }

    @Override
    public void dividends(DividendsTO to) throws SerException {
        dividendsSer.dividends(to);
    }

    @Override
    public List<DividendsBO> maps(DividendsDTO dto) throws SerException {
        return dividendsSer.maps(dto);
    }

    @Override
    public DividendsBO getById(String id) throws SerException {
        return dividendsSer.getById(id);
    }

    @Override
    public Long getTotal(DividendsDTO dto) throws SerException {
        return dividendsSer.getTotal(dto);
    }
}