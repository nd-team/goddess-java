package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.InvestTransferBO;
import com.bjike.goddess.moneyside.dto.InvestTransferDTO;
import com.bjike.goddess.moneyside.entity.InvestTransfer;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.InvestTransferTO;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;

/**
 * 投资转让业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InvestTransferSer extends Ser<InvestTransfer, InvestTransferDTO> {
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
     * 投资转让列表总条数
     */
    default Long countInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        return null;
    }

    /**
     * 一个投资转让
     *
     * @return class InvestTransferBO
     */
    default InvestTransferBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 投资转让
     *
     * @param investTransferDTO 投资转让dto
     * @return class InvestTransferBO
     * @throws SerException
     */
    default List<InvestTransferBO> findListInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        return null;
    }

    /**
     * 添加投资转让
     *
     * @param investTransferTO 投资转让数据to
     * @return class InvestTransferBO
     * @throws SerException
     */
    default InvestTransferBO insertInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        return null;
    }

    /**
     * 编辑投资转让
     *
     * @param investTransferTO 投资转让数据to
     * @return class InvestTransferBO
     * @throws SerException
     */
    default InvestTransferBO editInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除投资转让
     *
     * @param id
     * @throws SerException
     */
    default void removeInvestTransfer(String id) throws SerException {

    }

    /**
     * 获取所有投资转让人
     */
    List<UserBO> findUserListInOrgan() throws SerException;
}