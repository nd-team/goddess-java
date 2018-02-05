package com.bjike.goddess.dispatchcar.service;

import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.dispatchcar.bo.*;
import com.bjike.goddess.dispatchcar.dto.CollectDispatchcarDTO;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.dto.DispatchcarExportDTO;
import com.bjike.goddess.dispatchcar.entity.DispatchCarInfo;
import com.bjike.goddess.dispatchcar.enums.CollectIntervalType;
import com.bjike.goddess.dispatchcar.enums.CollectType;
import com.bjike.goddess.dispatchcar.excel.DispatchCarInfoSetExcel;
import com.bjike.goddess.dispatchcar.excel.SonPermissionObject;
import com.bjike.goddess.dispatchcar.to.*;

import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.user.bo.UserBO;

import java.time.LocalDate;
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
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

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
     /**
     * 资金核对意见
     *
     */
    void fundSugg(DispatchCarInfoTO dispatchCarInfoTO,PredictPayTO to) throws SerException;

    /**
     * 预算核对意见
     *
     */
    void budgetSugg(DispatchCarInfoTO dispatchCarInfoTO,CheckChangeCarTO to) throws SerException;


    /**
     * 客户模块负责人核对意见
     */
    void clientSugg(CheckChangeCarTO to) throws SerException;


    /**
     * 素养模块负责人核对意见
     */
    void headSugg(CheckChangeCarTO to) throws SerException;

    /**
     * 财务模块负责人核对意见
     */
    void financialSugg(DispatchCarInfoTO dispatchCarInfoTO,CheckChangeCarTO to) throws SerException;

    void financialSugg(CheckChangeCarTO to) throws SerException;


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
//    void receiptAudit(String id, String auditReceiptSugg, String receiveReceiptDate, Boolean auditReceiptResult) throws SerException;


    /**
     * 收到票据
     */
    void receivePaper(String id,Boolean isCorrect) throws SerException;

    /**
     * 寄件
     */
    void mail(MailTO to) throws SerException;


    /**
     * 付款
     *
     * @param id 出车记录id
     */
    void pay(String id) throws SerException;

    void pay(String id, Boolean pay) throws SerException;

    /**
     * 出车情况汇总
     * @param collectIntervalType
     * @param collectType
     * @return
     * @throws SerException
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
     * 支付司机金额汇总
     */
    List<PayDriverMoneyCollectBO> driverCollect(String startTime, String endTime, String project) throws SerException;

    /**
     * 付款计划
     *
     */
    void predict(PredictPayTO to) throws SerException;

    List<SonPermissionObject> financeSonPermission() throws SerException;

    Boolean financeGuidePermission(GuidePermissionTO to) throws SerException;

    List<DriverDispatchFeeBO> findDispatchFree(String area, String projectGroup, Integer year, Integer month) throws SerException;

    List<DriverDispatchsBO> findDispatchs(String area, String projectGroup, Integer year, Integer month) throws SerException;

    Double findOilAmount(String oilCardCode, Integer year, Integer month) throws SerException;

    /**
     * 查询所有司机信息
     *
     * @return
     * @throws SerException
     */
    List<DriverInfoBO> findDriver() throws SerException;


    /**
     * 查询所有用车陪同人员,任务下达人,用车人
     *
     * @throws SerException
     */
    List<UserBO> findAllEntry() throws SerException;


    /**
     * 查询所有油卡信息
     *
     * @throws SerException
     */
    List<OilCardBasicCarBO> findAllOil() throws SerException;

    /**
     * 汇总
     */
    List<PayedCollectBO> collectPayed(String startTime,String endTime) throws SerException;


    /**
     * 查找所有项目名称
     */
    List<String> findAllProject() throws SerException;

    /**
     * 出车有误记录列表
     */
    List<DispatchCarInfoBO> findWrongRecord(DispatchCarInfoDTO dto) throws SerException;

    /**
     * 出车有误记录编辑
     */
    void correctMistake(DispatchCarInfoTO to) throws SerException;

    /**
     * 导入
     *
     * @param toList
     * @throws SerException
     */
    default void leadExcel(List<DispatchCarInfoSetExcel> toList) throws SerException {
        return;
    }

    ;

    /**
     * 导出
     *
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] exportExcel(DispatchcarExportDTO dto) throws SerException;


    /**
     * 导出Excel模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 出车情况汇总导出功能
     */
    byte[] exportExcel(CollectIntervalType collectIntervalType,CollectType collectType,ExportCollectPayedTO to) throws SerException;




    /**
     * 把旧服务武器上的数据拷贝到本地
     */
//    void copyServer() throws SerException;

    /**
     * 把旧服务器上的数据司机信息拷贝到本地
     */
    void copyDriver() throws SerException;

    /**
     * 出车汇总
     */
    List<CollectDispatchcarBO> countCar(CollectDispatchcarDTO dispatchcarDTO) throws SerException;


    /**
     * 出车记录删除
     */
    void delete(String id) throws SerException;

    /**
     * 根据油卡编号，地区，部门，汇总时间查询出车记录
     */
    List<DispatchCarInfoBO> findInformation(String area,String department,String day) throws SerException;

    /**
     * 根据油卡编号，地区，部门，汇总时间区间查询出车记录
     */
    List<DispatchCarInfoBO> findInformation(String area, String department, LocalDate[] day) throws SerException;



    /**
     * 根据油卡编号，部门，汇总时间区间查询出车记录
     * @param department
     * @param day
     * @throws SerException
     */
    List<DispatchCarInfoBO> findInformation( String department, LocalDate[] day) throws SerException;

    /**
     * 根据司机名称获取用车油耗
     */
    Double findOilWear(String driver) throws SerException;

    /**
     * 根据油卡编号查询油卡余额
     */
    Double findBalance(String oilCardNumber) throws SerException;


    /**
     * 根据项目名称获取立项信息
     */
    Boolean findProjectAproval(String project) throws SerException;

    /**
     * 查询所有项目组
     */
    List<String> getAllDepartment() throws SerException;

    /**
     * 查询所有地区
     */
    List<AreaBO> findArea() throws SerException;

    /**
     * chenjunhao
     * 根据项目名称获取出车数量
     * @param project
     * @return
     * @throws SerException
     */
    Long dispatchCount(String project) throws SerException;

    /**
     * 获取出车编号
     *
     * @param
     * @return class
     * @version v1
     */
    String getDispatchNumber() throws SerException;

    /**
     * 申请重新审核
     *
     * @param
     * @return class
     * @version v1
     */
    void reAudit(DispatchCarInfoTO to) throws SerException;

    /**
     * 获取全部项目（本身项目）
     *
     * @param
     * @return class
     * @version v1
     */
    List<String> listProject() throws SerException;
}