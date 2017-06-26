package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.*;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.enums.CollectIntervalType;
import com.bjike.goddess.dispatchcar.enums.CollectType;
import com.bjike.goddess.dispatchcar.excel.SonPermissionObject;
import com.bjike.goddess.dispatchcar.to.ConditionTO;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.to.FinanceCollectTO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;

import java.util.List;

/**
 * 出车记录业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DispatchCarInfoAPI {

    /**
     * 新增出车记录
     *
     * @param to 出车记录
     * @return 出车记录
     */
    DispatchCarInfoBO addModel(DispatchCarInfoTO to) throws SerException;

    /**
     * 编辑出车记录
     *
     * @param to 出车记录
     * @return 出车记录
     */
    DispatchCarInfoBO editModel(DispatchCarInfoTO to) throws SerException;

    /**
     * 冻结出车记录
     *
     * @param id 出车记录Id
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻出车记录
     *
     * @param id 出车记录id
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 分页查询出车记录
     *
     * @param dto 分页条件
     * @return 出车记录结果集
     */
    List<DispatchCarInfoBO> pageList(DispatchCarInfoDTO dto) throws SerException;

    /**
     * 上传附件
     *
     * @param to 附件内容转存对象
     */
    void fileUpload(DispatchCarInfoTO to) throws SerException;

    /**
     * 根据id查询出车记录
     *
     * @param id 出车记录id
     * @return 出车记录
     */
    DispatchCarInfoBO findDetail(String id) throws SerException;

    /**
     * 根据id查询审核详情
     *
     * @param id 出车记录id
     * @return 审核详情结果集
     */
    AuditDetailBO findAudit(String id) throws SerException;

    /**
     * 资金核对意见
     *
     * @param id             出车记录id
     * @param fundModuleSugg 意见
     */
    void fundSugg(String id, String fundModuleSugg) throws SerException;

    /**
     * 预算核对意见
     *
     * @param id               出车记录id
     * @param budgetModuleSugg 意见
     */
    void budgetSugg(String id, String budgetModuleSugg) throws SerException;

    /**
     * 项目负责人或任务下发人审核
     *
     * @param id            出车记录id
     * @param principalSugg 出车记录id
     * @param auditResult   审核记过
     */
    void principalSugg(String id, String principalSugg, Boolean auditResult) throws SerException;

    /**
     * 票据审核
     *
     * @param id
     * @param auditReceiptSugg   审核意见
     * @param receiveReceiptDate 签收日期
     * @param auditReceiptResult 审核结果
     */
    void receiptAudit(String id, String auditReceiptSugg, String receiveReceiptDate, Boolean auditReceiptResult) throws SerException;

    /**
     * 付款
     *
     * @param id 出车记录id
     */
    void pay(String id) throws SerException;

    /**
     * 出车情况汇总
     *
     * @param collectIntervalType
     * @return 出车汇总结果集
     */
    List<DispatchCollectBO> dispatchCollect(CollectIntervalType collectIntervalType, CollectType collectType) throws SerException;

    /**
     * 财务周汇总
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 周汇总结果集
     */
    List<FinanceCollectBO> weekCollect(String startDate, String endDate) throws SerException;

    /**
     * 财务月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return 月汇总结果集
     */
    List<FinanceCollectBO> monthCollect(Integer year, Integer month) throws SerException;

    /**
     * 财务地区或项目组或司机汇总
     *
     * @param to 查询条件封装类
     * @return 地区汇总结果集
     */
    List<FinanceCollectBO> selectCollect(FinanceCollectTO to) throws SerException;

    /**
     * 根据id查询对应汇总的详情
     *
     * @param id 出车记录id
     * @return 汇总详情
     */
    FinanceCollectBO findCollectDetail(String id) throws SerException;

    /**
     * 财务地区或项目组或司机分析
     *
     * @param to
     * @return
     * @throws SerException
     */
    List<FinanceAnalyzeBO> selectAnalyze(FinanceCollectTO to) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 查询结果
     */
    Long count(DispatchCarInfoDTO dto) throws SerException;

    /**
     * 根据id查询出车记录
     *
     * @param id 出车记录id
     * @return 出车记录
     */
    DispatchCarInfoBO findById(String id) throws SerException;

    /**
     * 查询全部等待付款出车记录
     *
     * @return 等待付款出车记录
     */
    default List<DispatchCarInfoBO> allWaitPay() throws SerException {
        return null;
    }

    List<AuditResultBO> findAuditResult(String id) throws SerException;

    /**
     * 付款计划
     *
     * @param id            出车记录
     * @param budgetPayDate 预计付款时间
     * @param payPlan       付款计划
     */
    void predict(String id, String budgetPayDate, String payPlan) throws SerException;

    /**
     * 根据地区及项目组及项目名称及年份及月份查询出车记录
     *
     * @return
     */
    List<DispatchCarInfoBO> getByConfition(ConditionTO to) throws SerException;

    List<SonPermissionObject> financeSonPermission() throws SerException;

    Boolean financeGuidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
}