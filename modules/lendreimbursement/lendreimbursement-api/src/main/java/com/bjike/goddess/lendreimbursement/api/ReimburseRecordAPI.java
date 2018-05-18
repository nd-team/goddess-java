package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.AccountVoucherBO;
import com.bjike.goddess.lendreimbursement.bo.CollectReimerDataBO;
import com.bjike.goddess.lendreimbursement.bo.OptionBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.PhoneReimburseDTO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimCompanyShapeDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimburseShapeDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimburseShapeDetailDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimburseTrendShapeDTO;
import com.bjike.goddess.lendreimbursement.enums.ReimPhoneSelectStatus;
import com.bjike.goddess.lendreimbursement.enums.ReimPhoneShowStatus;
import com.bjike.goddess.lendreimbursement.excel.SonPermissionObject;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.PhoneReimbursePayTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimCompanyMixShapeVO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimShapeAllVO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimShapeMixVO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimShapeVO;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcelTO;

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
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(LendGuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取申请报销
     *
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 申请报销列表总条数
     */
    default Long countReimburseRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 报销列表总条数
     */
    default Long countReimburseRecords(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
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
     * 等待审核寄件
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO sendRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
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
     * 手机版等待付款预计付款
     *
     * @param reimburseRecordTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO phonePrePay(ReimburseRecordTO reimburseRecordTO) throws SerException {
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
     * 手机版的等待付款的付款
     *
     * @param phoneReimbursePayTO 申请报销信息
     * @return class ReimburseRecordBO
     */
    default ReimburseRecordBO phoneWaitPay(PhoneReimbursePayTO phoneReimbursePayTO) throws SerException {
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
     * @return class CollectReimerDataBO
     */
    default List<CollectReimerDataBO> collectLender(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 汇总地区
     *
     * @param reimburseRecordDTO reimburseRecordDTO
     * @return class CollectReimerDataBO
     */
    default List<CollectReimerDataBO> collectArea(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 汇总一级科目
     *
     * @param reimburseRecordDTO reimburseRecordDTO
     * @return class CollectReimerDataBO
     */
    default List<CollectReimerDataBO> collectFirstSubject(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    /**
     * 汇总项目名称
     *
     * @param reimburseRecordDTO reimburseRecordDTO
     * @return class CollectReimerDataBO
     */
    default List<CollectReimerDataBO> collectProjectName(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }


    /**
     * 所有报销人
     */
    default List<String> listAllUser() throws SerException {
        return null;
    }

    /**
     * 预计付款的报销单号
     */
    default List<String> reimNumByPrepay() throws SerException {
        return null;
    }

    /**
     * 所有一级科目
     */
    default List<String> listFirstSubject() throws SerException {
        return null;
    }

    /**
     * 所有地区
     */
    default List<String> listArea() throws SerException {
        return null;
    }

    /**
     * 所有项目
     */
    default List<String> listProject() throws SerException {
        return null;
    }

    /**
     * 报销人汇总的报销人条件
     */
    default List<String> listReimUser() throws SerException {
        return null;
    }

    /**
     * 等待付款导出excel
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    ;

    /**
     * 已付款记录导出excel
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    default byte[] exportAlPayExcel(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    ;

    /**
     * chenjunhao
     * 等待付款导出cjh
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    default List<ExportExcelTO> exportExcelCjh(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    ;

    /**
     * chenjunhao
     * 等待付款列表
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    default List<ReimburseRecordBO> listWaitPayCJH(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    ;

    /**
     * chenjunhao
     * 付款
     *
     * @param reimburseRecordTO
     * @return
     * @throws SerException
     */
    default ReimburseRecordBO waitPayCJH(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return null;
    }

    ;

    /**
     * chenjunhao
     *
     * @param reimburseRecordDTO
     * @return
     * @throws SerException
     */
    default Long countWaitPayCJH(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return null;
    }

    ;


    /**
     * 手机端的列表
     *
     * @return class ReimburseRecordBO
     */
    default List<ReimburseRecordBO> listAll(PhoneReimburseDTO phoneReimburseDTO) throws SerException {
        return null;
    }

    /**
     * 手机端的详情按钮控件状态
     *
     * @return class ReimPhoneShowStatus
     */
    default ReimPhoneShowStatus phoneShowRight(ReimPhoneSelectStatus reimPhoneSelectStatus, String reimId) throws SerException {
        return null;
    }


    /**
     * 汇总个人周月年数据图
     *
     * @param reimburseShapeDTO
     * @return
     * @throws SerException
     */
    default ReimShapeAllVO collectSelfShape(ReimburseShapeDTO reimburseShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总任意两月的变化趋势图
     *
     * @param reimburseTrendShapeDTO
     * @return
     * @throws SerException
     */
    default ReimShapeMixVO collectSelfTrend(ReimburseTrendShapeDTO reimburseTrendShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总公司项目组时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO
     * @return
     * @throws SerException
     */
    default ReimCompanyMixShapeVO collectGroupBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总公司项目时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO
     * @return
     * @throws SerException
     */
    default ReimCompanyMixShapeVO collectProjectBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总公司地区时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO
     * @return
     * @throws SerException
     */
    default ReimCompanyMixShapeVO collectAreaBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return null;
    }


    /**
     * 汇总公司地区时间段内的特定指标统计图
     *
     * @param reimburseShapeDetailDTO
     * @return
     * @throws SerException
     */
    default ReimShapeVO collectAreaDetailBar(ReimburseShapeDetailDTO reimburseShapeDetailDTO) throws SerException {
        return null;
    }


    /**
     * 报销数据分析图
     *
     * @return
     * @throws SerException
     */
    default OptionBO analysisDiagram() throws SerException {
        return null;
    }
}