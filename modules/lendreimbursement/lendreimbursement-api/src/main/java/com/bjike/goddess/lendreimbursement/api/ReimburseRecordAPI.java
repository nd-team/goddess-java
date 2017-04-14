package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.AccountVoucherBO;
import com.bjike.goddess.lendreimbursement.bo.CollectDataBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;

import java.util.List;

/**
 * 报销记录业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReimburseRecordAPI {

    /**
     * 申请报销列表总条数
     */
    default Long countReimburseRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 申请报销列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listReimburseRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO addReimburseRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO editReimburseRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteReimburseRecord(String id) throws SerException {
        return;
    }

    ;


    /**
     * 获取详细
     *
     * @param id id
     */
    default ReimburseRecordBO getReimburseRecordById(String id) throws SerException {
        return null;
    }

    /**
     * 有误报销单列表总条数
     */
    default Long countErrorRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 有误报销单列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listErrorRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 编辑有误报销单
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO editErrorRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }

    /**
     * 等待审核报销单列表总条数
     */
    default Long countAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 等待审核报销单列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 审核等待审核
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO auditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }
    /**
     * 负责人确认冻结等待审核
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO congelAuditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }

    /**
     * 分析报销单列表总条数
     */
    default Long countAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 分析报销单列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 分析审核
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO analisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }
    /**
     * 分析人申请冻结等待审核
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO congelAnalisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }

    /**
     * 已经分析报销单列表总条数
     */
    default Long countHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 已经分析报销单列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }


    /**
     * 帐务核对列表总条数
     */
    default Long countAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 帐务核对列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 帐务核对收到单据
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO recieveTicketCondition(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }


    /**
     * 等待付款列表总条数
     */
    default Long countWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 等待付款列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 等待付款预计付款
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO prePay(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }

    /**
     * 等待付款的付款
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO waitPay(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }

    /**
     * 已付款记录列表总条数
     */
    default Long countHasPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 已付款记录列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listHasPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 已付款记录生成记账凭证
     *
     * @param id 已付款记录信息id
     * @return class AccountVoucherBO
     */
    default List<AccountVoucherBO> listAccountVoucherByRecord(String id) throws SerException {
        return null;
    }

    /**
     * 汇总借款人
     *
     * @param reimburseRecordDTO reimburseRecordDTO
     * @return class CollectDataBO
     */
    default List<CollectDataBO> collectLender(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 汇总地区
     *
     * @param reimburseRecordDTO reimburseRecordDTO
     * @return class CollectDataBO
     */
    default List<CollectDataBO> collectArea(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 汇总一级科目
     *
     * @param reimburseRecordDTO reimburseRecordDTO
     * @return class CollectDataBO
     */
    default List<CollectDataBO> collectFirstSubject(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 汇总项目名称
     *
     * @param reimburseRecordDTO reimburseRecordDTO
     * @return class CollectDataBO
     */
    default List<CollectDataBO> collectProjectName(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }


    /**
     * 所有报销人
     *
     */
    default List<String> listAllUser( ) throws SerException {
        return null;
    }
    /**
     * 所有一级科目
     *
     */
    default List<String> listFirstSubject( ) throws SerException {
        return null;
    }
    /**
     * 所有地区
     *
     */
    default List<String> listArea( ) throws SerException {
        return null;
    }
    /**
     * 所有项目
     *
     */
    default List<String> listProject( ) throws SerException {
        return null;
    }




}