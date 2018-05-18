package com.bjike.goddess.fundrecords.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundrecords.bo.*;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordDTO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.excel.SonPermissionObject;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.fundrecords.to.GuidePermissionTO;

import java.util.List;

/**
 * 资金流水业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FundRecordAPI {

    /**
     * 新增补填资金流水
     *
     * @param to 资金流水
     * @return 资金流水
     */
    FundRecordBO add(FundRecordTO to) throws SerException;

    /**
     * 编辑补填资金流水
     *
     * @param to 资金流水
     * @return 资金流水
     */
    FundRecordBO edit(FundRecordTO to) throws SerException;

    /**
     * 删除补填资金流水
     *
     * @param id 补填资金流水
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询资金流水
     *
     * @param dto 分页条件
     * @return 资金流水结果集
     */
    List<FundRecordBO> pageList(FundRecordDTO dto) throws SerException;

    FundRecordBO balanceCall(String years,String month)throws SerException;

    /**
     * 分页查询资金流水总记录数
     *
     * @param dto 分页条件
     * @return 资金流水结果集
     */
    Long count(FundRecordDTO dto) throws SerException;

    /**
     *
     * 列表
     * lijuntao
     * @param dto 分页条件
     * @return 列表
     */
    List<FundRecordBO> findList(FundRecordDTO dto) throws SerException;

    /**
     * 总条数
     * lijuntao
     * @param dto 分页条件
     * @return 列表
     */
    Long findCount(FundRecordDTO dto) throws SerException;



    /**
     * 月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return 月汇总结果集
     */
    MonthCollectBO month(Integer year, Integer month) throws SerException;

    /**
     * 条件汇总
     *
     * @param to 汇总条件
     * @return 汇总结果集
     */
    List<ConditionCollectBO> condition(CollectTO to) throws SerException;

    /**
     * 根据id查询资金流水
     *
     * @param id 资金流水id
     * @return 资金流水
     */
    FundRecordBO findById(String id) throws SerException;

    /**
     * 地区分析
     *
     * @param year  年份
     * @param month 月份
     * @param area  地区
     * @return
     */
    List<AreaAnalyzeBO> areaAnalyze(Integer year, Integer month, String area) throws SerException;

    /**
     * 项目组分析
     *
     * @param year  年份
     * @param month 月份
     * @param group 项目组
     * @return
     */
    List<GroupAnalyzeBO> groupAnalyze(Integer year, Integer month, String group) throws SerException;

    /**
     * 项目分析
     *
     * @param year    年份
     * @param month   月份
     * @param project 项目
     * @return
     */
    List<ProjectAnalyzeBO> projectAnalyze(Integer year, Integer month, String project) throws SerException;

    void leadExcel(List<FundRecordTO> toList) throws SerException;

    /**
     * 根据账户来源导出
     * @param dataSource 账户来源
     * @return
     * @throws SerException
     */
    byte[] exportExcelLJT(String dataSource) throws SerException;

    byte[] exportExcel(String startDate, String endDate)  throws SerException;

    byte[] exportExcelModule() throws SerException;

    List<SonPermissionObject> sonPermission() throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 账户来源下拉值
     * @return
     * @throws SerException
     */
    List<String> sourceAccountValue() throws SerException;

    /**
     * 导入资金流水
     * @return
     * @throws SerException
     */
    void exportFund() throws SerException;

    /**
     * 日期汇总xiexiaoting
     *
     * @param startTime  开始时间
     * @param endTime 结束时间
     * @return 月汇总结果集
     */
    List<FundRecordCollectBO> monthSumma(String startTime, String endTime) throws SerException;
//    MonthCollectBO monthSumma(Integer year, Integer month) throws SerException;

    /**
     * 日期汇总
     * @param dto
     * @throws SerException
     */
    MonthCollectBO dateCollect(CapitalFlowRecordDTO dto) throws SerException;


    /**
     * 地区汇总xiexiaoting
     *
     * @param startTime  开始时间
     * @param endTime 结束时间
     * @param area 地区
     * @return 地区汇总结果集
     */
    List<ConditionCollectBO> areaSumma(String startTime, String endTime,String area) throws SerException;

    /**
     * 项目组汇总xiexiaoting
     *
     * @param startTime  开始时间
     * @param endTime 结束时间
     * @param project 项目组
     * @return 项目组汇总结果集
     */
    List<ConditionCollectBO> projectSumma(String startTime, String endTime,String project) throws SerException;

    /**
     * 项目名称汇总xiexiaoting
     *
     * @param startTime  开始时间
     * @param endTime 结束时间
     * @param projectName 项目名称
     * @return 项目组汇总结果集
     */
    List<ConditionCollectBO> projectNameSumma(String startTime, String endTime,String projectName) throws SerException;

    /**
     * 获取所有地区xiexiaoting
     * @return
     * @throws SerException
     */
    default List<String> findAllArea() throws SerException{return null;}

    /**
     * 获取所有项目组xiexiaoting
     * @return
     * @throws SerException
     */
    default List<String> findAllProjectGroup() throws SerException{return null;}

    /**
     * 获取所有项目名称xiexiaoting
     * @return
     * @throws SerException
     */
    default List<String> findAllProjectName() throws SerException{return null;}

    /**
     * 地区分析xiexiaoting
     *
     * @param startTime  开始时间
     * @param endTime 结束时间
     * @param area 地区
     * @return 地区分析结果集
     */
    List<AreaAnalyzeBO> areaAnalysis(String startTime, String endTime,String  area) throws SerException;
//    AreaAnalyzeBO areaAnalysis(Integer year, Integer month,String area) throws SerException;

    /**
     * 项目组分析xiexiaoting
     *
     * @param startTime  开始时间
     * @param endTime 结束时间
     * @param project 项目组
     * @return 项目组分析结果集
     */
    List<GroupAnalyzeBO> projectAnalysis(String startTime, String endTime,String project) throws SerException;

    /**
     * 项目名称分析xiexiaoting
     *
     * @param startTime  开始时间
     * @param endTime 结束时间
     * @param projectName 项目名称
     * @return 项目名称分析结果集
     */
    List<ProjectAnalyzeBO> projectNameAnalysis(String startTime, String endTime,String projectName) throws SerException;
//    ProjectAnalyzeBO projectNameAnalysis(Integer year, Integer month,String projectName) throws SerException;

    /**
     * 获取所有资金流水列表xiexiaoting
     * @param dto
     * @throws SerException
     */
    List<FundRecordBO> allFundRecord(FundRecordDTO dto)throws SerException;

    /**
     * 获取所有账户来源
     * @return
     * @throws SerException
     */
    default List<String> findAllDataSource() throws SerException{return null;}
    /**
     * 按日期汇总导出
     * @return
     * @throws SerException
     */
    byte [] monthSummaExportE1xcel(String startTime, String endTime)throws SerException;
    /**
     * 按地区汇总导出
     * @return
     * @throws SerException
     */
    byte [] areaSummaExportExcel(String startTime, String endTime,String area)throws SerException;
    /**
     * 按项目组汇总导出
     * @return
     * @throws SerException
     */
    byte [] projectSummaExportExcel(String startTime, String endTime,String project)throws SerException;
    /**
     * 按项目汇总导出
     * @return
     * @throws SerException
     */
    byte [] projectNameSummaExportExcel(String startTime, String endTime,String projectName)throws SerException;
}