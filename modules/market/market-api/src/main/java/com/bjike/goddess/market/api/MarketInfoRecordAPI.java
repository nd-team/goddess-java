package com.bjike.goddess.market.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerNameNumBO;
import com.bjike.goddess.market.bo.MarketInfoRecordBO;
import com.bjike.goddess.market.bo.OptionBO;
import com.bjike.goddess.market.bo.SummationAreaBO;
import com.bjike.goddess.market.bo.SummationBO;
import com.bjike.goddess.market.dto.MarketInfoRecordDTO;
import com.bjike.goddess.market.excel.SonPermissionObject;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoRecordTO;

import java.util.List;

/**
 * 市场信息记录业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 11:50 ]
 * @Description: [ 市场信息记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketInfoRecordAPI {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 市场信息记录列表总条数
     */
    default Long countRecord(MarketInfoRecordDTO marketInfoRecordDTO) throws SerException {
        return null;
    }

    /**
     * 一个市场信息记录
     *
     * @return class MarketInfoRecordBO
     */
    default MarketInfoRecordBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 市场信息记录
     *
     * @param marketInfoRecordDTO 市场信息记录dto
     * @return class MarketInfoRecordBO
     * @throws SerException
     */
    default List<MarketInfoRecordBO> findListRecord(MarketInfoRecordDTO marketInfoRecordDTO) throws SerException {
        return null;
    }

    /**
     * 添加市场信息记录
     *
     * @param marketInfoRecordTO 市场信息记录数据to
     * @return class MarketInfoRecordBO
     * @throws SerException
     */
    default MarketInfoRecordBO insertRecord(MarketInfoRecordTO marketInfoRecordTO) throws SerException {
        return null;
    }

    /**
     * 编辑市场信息记录
     *
     * @param marketInfoRecordTO 市场信息记录数据to
     * @return class MarketInfoRecordBO
     * @throws SerException
     */
    default MarketInfoRecordBO editRecord(MarketInfoRecordTO marketInfoRecordTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除市场信息记录
     *
     * @param id
     * @throws SerException
     */
    default void removeRecord(String id) throws SerException {

    }
    /**
     * 获取所有用户
     *
     * @return
     * @throws SerException
     */
    default List<String> findallUser() throws SerException {
        return null;
    }
    /**
     * 获取所有的客户姓名和客户编号lijuntao
     *
     * @return class String
     */
    default List<CustomerNameNumBO> getNameNum() throws SerException {
        return null;
    }

    /**
     * 获取所有的竞争对手lijuntao
     *
     * @return class String
     */
    default List<String> getCompetName() throws SerException {
        return null;
    }
    /**
     * 获取所有的业务类型
     *
     * @return class String
     */
    default List<String> findBussType() throws SerException {
        return null;
    }

    /**
     * 根据业务类型获取地区
     *
     * @return class String
     */
    default List<String> findAreaByBussType(String bussType) throws SerException {
        return null;
    }


    /**
     * 市场信息管理日汇总
     *
     * @param summDate 日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaDay(String summDate) throws SerException {
        return null;
    }

    /**
     * 市场信息管理日汇总加个地区
     *
     * @param summDate 日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationAreaBO> summaDayByArea(String summDate) throws SerException {
        return null;
    }

    /**
     * 市场信息管理周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 市场信息管理周汇总加个地区
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationAreaBO
     * @throws SerException
     */
    default List<SummationAreaBO> summaWeekByArea(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 市场信息管理月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 市场信息管理月汇总加个地区
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationAreaBO
     * @throws SerException
     */
    default List<SummationAreaBO> summaMonthByArea(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 市场信息管理季度汇总
     *
     * @param quarter 季度
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaQuarter(Integer year,Integer quarter) throws SerException {
        return null;
    }

    /**
     * 市场信息管理季度汇总加个地区
     *
     * @param quarter 季度
     * @return class SummationAreaBO
     * @throws SerException
     */
    default List<SummationAreaBO> summaQuarterByArea(Integer year,Integer quarter) throws SerException {
        return null;
    }

    /**
     * 市场信息管理年度汇总
     *
     * @param year 年度
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 市场信息管理年度汇总加个地区
     *
     * @param year 年度
     * @return class SummationAreaBO
     * @throws SerException
     */
    default List<SummationAreaBO> summaYearByArea(Integer year) throws SerException {
        return null;
    }

    /**
     * 市场信息管理累计汇总
     *
     * @param endDate 截止日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaTotal(String endDate) throws SerException {
        return null;
    }

    /**
     * 市场信息管理累计汇总加个地区
     *
     * @param endDate 截止日期
     * @return class SummationAreaBO
     * @throws SerException
     */
    default List<SummationAreaBO> summaTotalByArea(String endDate) throws SerException {
        return null;
    }

    /**
     * 市场信息图形展示日汇总数据
     * @param summDate 日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowDay(String summDate) throws SerException{
        return null;
    }
    /**
     * 市场信息图形展示周汇总数据
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowWeek(Integer year,Integer month,Integer week) throws SerException{
        return null;
    }
    /**
     * 市场信息图形展示月汇总数据
     * @param year 年份
     * @param month 月份
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowMonth(Integer year,Integer month) throws SerException{
        return null;
    }
    /**
     * 市场信息图形展示季度汇总数据
     * @param year 年份
     * @param quarter 季度
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowQuarter(Integer year,Integer quarter) throws SerException{
        return null;
    }
    /**
     * 市场信息图形展示年度汇总数据
     * @param year 年份
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowYear(Integer year) throws SerException{
        return null;
    }
    /**
     * 市场信息图形展示累计汇总数据
     * @param endDate 截止日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowTotal(String endDate) throws SerException{
        return null;
    }
}