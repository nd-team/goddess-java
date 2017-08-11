package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffshares.bo.CompanySchemeBO;
import com.bjike.goddess.staffshares.bo.DividendsBO;
import com.bjike.goddess.staffshares.dto.DividendsDTO;
import com.bjike.goddess.staffshares.entity.Dividends;
import com.bjike.goddess.staffshares.to.DividendsTO;

import java.util.List;

/**
 * 干股分红表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 干股分红表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DividendsSer extends Ser<Dividends, DividendsDTO> {
    /**
     * 公司干股交易情况
     *
     * @return
     * @throws SerException
     */
    default List<CompanySchemeBO> detail() throws SerException {
        return null;
    }

    /**
     * 分红
     *
     * @param to
     * @throws SerException
     */
    default void dividends(DividendsTO to) throws SerException {
        return;
    }

    /**
     * 干股分红列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<DividendsBO> maps(DividendsDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取干股分红
     *
     * @param id
     * @throws SerException
     */
    default DividendsBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(DividendsDTO dto) throws SerException {
        return null;
    }

}