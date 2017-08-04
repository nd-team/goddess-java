package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.*;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.excel.SonPermissionObject;
import com.bjike.goddess.materialsummary.to.GuidePermissionTO;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;

import java.util.List;

/**
 * 物质购买发送邮件业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买发送邮件业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SendEmailAPI {

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
     * 总条数
     */
    default Long counts(SendEmailDTO SendEmailDTO) throws SerException {
        return null;
    }


    /**
     * 一个个邮件
     *
     * @return class SendEmailBO
     */
    default SendEmailBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 邮件汇总列表
     *
     * @return class SendEmailBO
     */
    default List<SendEmailBO> listCollectEmail(SendEmailDTO SendEmailDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param SendEmailTO 邮件汇总信息
     * @return class BuySendEmailBO
     */
    default SendEmailBO addCollectEmail(SendEmailTO SendEmailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param SendEmailTO 邮件汇总信息
     * @return class BuySendEmailBO
     */
    default SendEmailBO editCollectEmail(SendEmailTO SendEmailTO) throws SerException {
        return null;
    }

    /**
     * 删除邮件汇总
     *
     * @param id id
     */
    default void deleteCollectEmail(String id) throws SerException {
        return;
    }


    /**
     * 冻结邮件汇总
     *
     * @param id id
     */
    default void congealCollectEmail(String id) throws SerException {
        return;
    }


    /**
     * 解冻邮件汇总
     *
     * @param id id
     */
    default void thawCollectEmail(String id) throws SerException {
        return;
    }
    /**
     * 根据模块名获取所有的汇总类型
     *
     * @param moduleType
     */
    default List<SummaryType> summaryType(ModuleType moduleType) throws SerException {
        return null;
    }

    /**
     * 针对各物资分类购买情况日汇总
     *
     * @param summTime
     */
    default List<TypeBuySummBO> typeBuySummDay(String summTime) throws SerException {
        return null;
    }
    /**
     * 针对各物资分类购买情况周汇总
     *
     * @param year
     * @param month
     * @param week
     */
    default List<TypeBuySummBO> typeBuySummWeek(Integer year,Integer month,Integer week) throws SerException {
        return null;
    }
    /**
     * 针对各物资分类购买情况月汇总
     *
     * @param year
     * @param month
     */
    default List<TypeBuySummBO> typeBuySummMonth(Integer year,Integer month) throws SerException {
        return null;
    }
    /**
     * 针对各物资分类购买情况年汇总
     *
     * @param year
     */
    default List<TypeBuySummBO> typeBuySummYear(Integer year) throws SerException {
        return null;
    }
    /**
     * 针对各部门地区物资购买情况日汇总
     *
     * @param summTime
     */
    default List<TypeBuySummBO> areaBuySummDay(String summTime) throws SerException {
        return null;
    }
    /**
     * 针对各部门地区物资购买情况周汇总
     *
     * @param year
     * @param month
     * @param week
     */
    default List<TypeBuySummBO> areaBuySummWeek(Integer year,Integer month,Integer week) throws SerException {
        return null;
    }
    /**
     * 针对各部门地区物资购买情况月汇总
     *
     * @param year
     * @param month
     */
    default List<TypeBuySummBO> areaBuySummMonth(Integer year,Integer month) throws SerException {
        return null;
    }
    /**
     * 针对各部门地区物资购买情况年汇总
     *
     * @param year
     */
    default List<TypeBuySummBO> areaBuySummYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 个人物资购买情况日汇总
     *
     * @param summTime
     */
    default List<PersonalBuySummBO> personBuySummDay(String summTime) throws SerException {
        return null;
    }
    /**
     * 个人物资购买情况周汇总
     *
     * @param year
     * @param month
     * @param week
     */
    default List<PersonalBuySummBO> personBuySummWeek(Integer year,Integer month,Integer week) throws SerException {
        return null;
    }
    /**
     * 个人物资购买情况月汇总
     *
     * @param year
     * @param month
     */
    default List<PersonalBuySummBO> personBuySummMonth(Integer year,Integer month) throws SerException {
        return null;
    }
    /**
     * 个人物资购买情况年汇总
     *
     * @param year
     */
    default List<PersonalBuySummBO> personBuySummYear(Integer year) throws SerException {
        return null;
    }
    /**
     * 整体针对入库来源的物资日汇总
     *
     * @param summTime
     */
    default List<ResouceStockSummBO> sourStockSummDay(String summTime) throws SerException {
        return null;
    }
    /**
     * 整体针对入库来源的物资周汇总
     *
     * @param year
     * @param month
     * @param week
     */
    default List<ResouceStockSummBO> sourStockSummWeek(Integer year,Integer month,Integer week) throws SerException {
        return null;
    }
    /**
     * 整体针对入库来源的物资月汇总
     *
     * @param year
     * @param month
     */
    default List<ResouceStockSummBO> sourStockSummMonth(Integer year,Integer month) throws SerException {
        return null;
    }
    /**
     * 整体针对入库来源的物资年汇总
     *
     * @param year
     */
    default List<ResouceStockSummBO> sourStockSummYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 各地区入库情况日汇总
     *
     * @param summTime
     */
    default List<AreaStockSummBO> areaStockSummDay(String summTime) throws SerException {
        return null;
    }
    /**
     * 各地区入库情况周汇总
     *
     * @param year
     * @param month
     * @param week
     */
    default List<AreaStockSummBO> areaStockSummWeek(Integer year,Integer month,Integer week) throws SerException {
        return null;
    }
    /**
     * 各地区入库情况月汇总
     *
     * @param year
     * @param month
     */
    default List<AreaStockSummBO> areaStockSummMonth(Integer year,Integer month) throws SerException {
        return null;
    }
    /**
     * 各地区入库情况年汇总
     *
     * @param year
     */
    default List<AreaStockSummBO> areaStockSummYear(Integer year) throws SerException {
        return null;
    }
    /**
     * 针对维修状态分类情况日汇总
     *
     * @param summTime
     */
    default List<StatusDeviceSummBO> statusDeviceSummDay(String summTime) throws SerException {
        return null;
    }
    /**
     * 针对维修状态分类情况周汇总
     *
     * @param year
     * @param month
     * @param week
     */
    default List<StatusDeviceSummBO> statusDeviceSummWeek(Integer year,Integer month,Integer week) throws SerException {
        return null;
    }
    /**
     * 针对维修状态分类情况月汇总
     *
     * @param year
     * @param month
     */
    default List<StatusDeviceSummBO> statusDeviceSummMonth(Integer year,Integer month) throws SerException {
        return null;
    }
    /**
     * 针对维修状态分类情况年汇总
     *
     * @param year
     */
    default List<StatusDeviceSummBO> statusDeviceSummYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 针对保修状态分类情况日汇总
     *
     * @param summTime
     */
    default List<WarrantyDeviceSummBO> warranDeviceSummDay(String summTime) throws SerException {
        return null;
    }
    /**
     * 针对保修状态分类情况周汇总
     *
     * @param year
     * @param month
     * @param week
     */
    default List<WarrantyDeviceSummBO> warranDeviceSummWeek(Integer year,Integer month,Integer week) throws SerException {
        return null;
    }
    /**
     * 针对保修状态分类情况月汇总
     *
     * @param year
     * @param month
     */
    default List<WarrantyDeviceSummBO> warranDeviceSummMonth(Integer year,Integer month) throws SerException {
        return null;
    }
    /**
     * 针对保修状态分类情况年汇总
     *
     * @param year
     */
    default List<WarrantyDeviceSummBO> warranDeviceSummYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 定时器检测要发送的邮件
     *
     */
    default void checkSendEmail( ) throws SerException {
        return ;
    }
}