package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffshares.bo.CompanySchemeBO;
import com.bjike.goddess.staffshares.bo.DividendsBO;
import com.bjike.goddess.staffshares.bo.DividendsConditionsBO;
import com.bjike.goddess.staffshares.bo.DividendsDetailBO;
import com.bjike.goddess.staffshares.dto.DividendsDTO;
import com.bjike.goddess.staffshares.entity.Dividends;
import com.bjike.goddess.staffshares.to.DividendsTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;

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
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
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

    /**
     * 确认分红
     *
     * @param to
     * @throws SerException
     */
    default void confirm(DividendsTO to) throws SerException {
        return;
    }

    /**
     * 交易持股明细列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<DividendsDetailBO> list(DividendsDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取交易持股明细
     *
     * @param id
     * @return
     * @throws SerException
     */
    default DividendsDetailBO find(String id) throws SerException {
        return null;
    }

    /**
     * 获取交易持股明细总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getCount(DividendsDTO dto) throws SerException {
        return null;
    }

    /**
     * 交易持股明细汇总
     *
     * @return
     * @throws SerException
     */
    default List<DividendsDetailBO> collect() throws SerException {
        return null;
    }

    /**
     * 公司干股情况汇总
     *
     * @return
     * @throws SerException
     */
    default List<DividendsConditionsBO> detailList() throws SerException {
        return null;
    }
}