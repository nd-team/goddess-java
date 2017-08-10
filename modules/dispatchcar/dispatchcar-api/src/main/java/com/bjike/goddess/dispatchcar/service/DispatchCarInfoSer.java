package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.dispatchcar.bo.*;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.entity.DispatchCarInfo;
import com.bjike.goddess.dispatchcar.enums.CollectIntervalType;
import com.bjike.goddess.dispatchcar.enums.CollectType;
import com.bjike.goddess.dispatchcar.excel.SonPermissionObject;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.to.FinanceCollectTO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;

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
public interface DispatchCarInfoSer extends Ser<DispatchCarInfo, DispatchCarInfoDTO> {

    /**
     * 新增出车记录
     *
     * @param to 出车记录
     * @return 出车记录
     */
    DispatchCarInfoBO insertModel(DispatchCarInfoTO to) throws SerException;

    /**
     * 编辑出车记录
     *
     * @param to 出车记录
     * @return 出车记录
     */
    DispatchCarInfoBO updateModel(DispatchCarInfoTO to) throws SerException;

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
     * @throws SerException
     */
    void fileUpload(DispatchCarInfoTO to) throws SerException;

    /**
     * 查询审核详情
     *
     * @param id 出车记录id
     * @return 审核详情结果集
     */
    AuditDetailBO findAudit(String id) throws SerException;

    /**
     * 查询审核结果
     *
     * @param id 出车记录id
     * @return 审核结果集
     */
    List<AuditResultBO> findAuditResults(String id) throws SerException;

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

    ;

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
     * 财务地区或项目或司机汇总
     *
     * @param to 查询条件封装类
     * @return 地区汇总结果集
     */
    List<FinanceCollectBO> selectCollect(FinanceCollectTO to) throws SerException;

    /**
     * 财务地区或项目或司机分析
     *
     * @param to 查询条件封装类
     * @return 地区汇总结果集
     */
    List<FinanceAnalyzeBO> selectAnalyze(FinanceCollectTO to) throws SerException;

    /**
     * 根据id查询对应汇总的详情
     *
     * @param id 出车记录id
     * @return 汇总详情
     */
    FinanceCollectBO findCollectDetail(String id) throws SerException;

    /**
     * 付款计划
     *
     * @param id            出车记录
     * @param budgetPayDate 预计付款时间
     * @param payPlan       付款计划
     */
    void predict(String id, String budgetPayDate, String payPlan) throws SerException;

    List<SonPermissionObject> financeSonPermission() throws SerException;

    Boolean financeGuidePermission(GuidePermissionTO to) throws SerException;
    List<DriverDispatchFeeBO> findDispatchFree(String area,String projectGroup,Integer year,Integer month) throws SerException;

    List<DriverDispatchsBO> findDispatchs(String area,String projectGroup,Integer year,Integer month) throws SerException ;

    Double findOilAmount(String oilCardCode, Integer year, Integer month) throws SerException;

    /**
     * 查询所有司机信息
     * @return
     * @throws SerException
     */
    List<DriverInfoBO> findDriver() throws SerException;


    /**
     * 查询所有用车陪同人员,任务下达人,用车人
     * @throws SerException
     */
    List<EntryBasicInfoBO> findAllEntry() throws SerException;


    /**
     * 查询所有油卡信息
     * @throws SerException
     */
    List<OilCardBasicBO> findAllOil() throws SerException;
}