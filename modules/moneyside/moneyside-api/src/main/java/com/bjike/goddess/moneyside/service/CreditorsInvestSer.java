package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.CreditorsInvestBO;
import com.bjike.goddess.moneyside.dto.CreditorsInvestDTO;
import com.bjike.goddess.moneyside.entity.CreditorsInvest;
import com.bjike.goddess.moneyside.to.CreditorsInvestTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;

import java.util.List;

/**
 * 投资条件-债权投资业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:33 ]
 * @Description: [ 投资条件-债权投资业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CreditorsInvestSer extends Ser<CreditorsInvest, CreditorsInvestDTO> {
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
     * 债权投资列表总条数
     */
    default Long countCreditorsInvest(CreditorsInvestDTO creditorsInvestDTO) throws SerException {
        return null;
    }

    /**
     * 一个债权投资
     *
     * @return class CreditorsInvestBO
     */
    default CreditorsInvestBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 债权投资
     *
     * @param creditorsInvestDTO 债权投资dto
     * @return class CreditorsInvestBO
     * @throws SerException
     */
    default List<CreditorsInvestBO> findListCreditorsInvest(CreditorsInvestDTO creditorsInvestDTO) throws SerException {
        return null;
    }

    /**
     * 添加债权投资
     *
     * @param creditorsInvestTO 债权投资数据to
     * @return class CreditorsInvestBO
     * @throws SerException
     */
    default CreditorsInvestBO insertCreditorsInvest(CreditorsInvestTO creditorsInvestTO) throws SerException {
        return null;
    }

    /**
     * 编辑债权投资
     *
     * @param creditorsInvestTO 债权投资数据to
     * @return class CreditorsInvestBO
     * @throws SerException
     */
    default CreditorsInvestBO editCreditorsInvest(CreditorsInvestTO creditorsInvestTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除债权投资
     *
     * @param id
     * @throws SerException
     */
    default void removeCreditorsInvest(String id) throws SerException {

    }
}