package com.bjike.goddess.fundrecords.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundrecords.bo.*;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;

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

    /**
     * 分页查询资金流水总记录数
     *
     * @param dto 分页条件
     * @return 资金流水结果集
     */
    Long count(FundRecordDTO dto) throws SerException;

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
     * 分析
     *
     * @param to 分析条件
     * @return 分析结果集
     */
    List<AnalyzeBO> analyze(CollectTO to) throws SerException;

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
}