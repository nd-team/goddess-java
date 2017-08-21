package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.CreditorsInvestBO;
import com.bjike.goddess.moneyside.bo.EquityInvestBO;
import com.bjike.goddess.moneyside.dto.CreditorsInvestDTO;
import com.bjike.goddess.moneyside.dto.EquityInvestDTO;
import com.bjike.goddess.moneyside.entity.EquityInvest;
import com.bjike.goddess.moneyside.to.CreditorsInvestTO;
import com.bjike.goddess.moneyside.to.EquityInvestTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;

import java.util.List;

/**
 * 投资条件-股权投资业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:38 ]
 * @Description: [ 投资条件-股权投资业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EquityInvestSer extends Ser<EquityInvest, EquityInvestDTO> {
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
     * 股权投资列表总条数
     */
    default Long countEquityInvest(EquityInvestDTO equityInvestDTO) throws SerException {
        return null;
    }

    /**
     * 一个股权投资
     *
     * @return class EquityInvestBO
     */
    default EquityInvestBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股权投资
     *
     * @param equityInvestDTO 股权投资dto
     * @return class EquityInvestBO
     * @throws SerException
     */
    default List<EquityInvestBO> findListEquityInvest(EquityInvestDTO equityInvestDTO) throws SerException {
        return null;
    }

    /**
     * 添加股权投资
     *
     * @param equityInvestTO 股权投资数据to
     * @return class EquityInvestBO
     * @throws SerException
     */
    default EquityInvestBO insertEquityInvest(EquityInvestTO equityInvestTO) throws SerException {
        return null;
    }

    /**
     * 编辑股权投资
     *
     * @param equityInvestTO 股权投资数据to
     * @return class EquityInvestBO
     * @throws SerException
     */
    default EquityInvestBO editEquityInvest(EquityInvestTO equityInvestTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除股权投资
     *
     * @param id
     * @throws SerException
     */
    default void removeEquityInvest(String id) throws SerException {

    }
}