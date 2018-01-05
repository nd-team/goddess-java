package com.bjike.goddess.checkfunds.service;


import com.bjike.goddess.bankrecords.bo.BankRecordPageListBO;
import com.bjike.goddess.checkfunds.bo.*;
import com.bjike.goddess.checkfunds.dto.BankReconciliationDTO;
import com.bjike.goddess.checkfunds.entity.BankReconciliation;
import com.bjike.goddess.checkfunds.to.BankReconciliationTO;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;
import java.util.Set;

/**
 * 银企对账（核对）业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 03:53 ]
 * @Description: [ 银企对账（核对）业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BankReconciliationSer extends Ser<BankReconciliation, BankReconciliationDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 经办
     *
     * @param to 银企对账（核对）to
     * @return
     * @throws SerException
     */
    BankReconciliationBO save(BankReconciliationTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto 银企对账（核对）dto
     * @return
     * @throws SerException
     */
    List<BankReconciliationBO> list(BankReconciliationDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    BankReconciliationBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    Long countNum(BankReconciliationDTO dto) throws SerException;

    /**
     * 余额调节列表
     *
     * @param id 对账id
     * @return
     * @throws SerException
     */
    List<RemainAdjustBO> boList(String id) throws SerException;

    /**
     * 确认调整
     *
     * @param id      对账id
     * @param balance 调整余额
     * @throws SerException
     */
    void confirmAdjust(String id, Double balance) throws SerException;

    /**
     * 查找所有用户名
     *
     * @return
     * @throws SerException
     */
    Set<String> allNames() throws SerException;

    /**
     * 提交
     *
     * @param id id
     * @throws SerException
     */
    void commit(String id) throws SerException;

    /**
     * 通过审批
     *
     * @param id
     * @throws SerException
     */
    void aduitPass(String id) throws SerException;

    /**
     * 不通过审批
     *
     * @param id
     * @throws SerException
     */
    void aduitNotPass(String id) throws SerException;

    /**
     * 余额调节
     *
     * @param id
     * @return
     * @throws SerException
     */
    List<RemainAdjustBO> adjust(String id) throws SerException;

    /**
     * 查找资金流水明细
     *
     * @param id 银行对账id
     * @return
     * @throws SerException
     */
    List<FundDetailBO> fundDetail(String id) throws SerException;

    /**
     * 查找银行流水明细
     *
     * @param id 银行对账id
     * @return
     * @throws SerException
     */
    List<BankRecordPageListBO> bankDetail(String id) throws SerException;

    /**
     * 借方差异
     *
     * @param id 银行对账id
     * @return
     * @throws SerException
     */
    List<DebtorDifferBO> debtorDiffer(String id) throws SerException;

    /**
     * 贷方差异
     *
     * @param id 银行对账id
     * @return
     * @throws SerException
     */
    List<CreditorDifferBO> creditorDiffer(String id) throws SerException;
}