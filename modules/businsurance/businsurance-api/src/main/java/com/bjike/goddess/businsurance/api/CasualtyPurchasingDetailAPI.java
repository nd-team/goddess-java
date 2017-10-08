package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.CasualtyPurchasingDetailBO;
import com.bjike.goddess.businsurance.bo.SummaryBO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingDetailDTO;
import com.bjike.goddess.businsurance.to.CasualtyPurchasingDetailTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 团体意外险购买详情业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CasualtyPurchasingDetailAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }


    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 团体意外险购买详情总条数
     */
    default Long countDetail(CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO) throws SerException {
        return null;
    }

    /**
     * 一个团体意外险购买详情
     */
    default CasualtyPurchasingDetailBO getOneDetail(String id) throws SerException {
        return null;
    }

    /**
     * 团体意外险购买详情
     *
     * @return class CasualtyPurchasingDetailBO
     */
    default List<CasualtyPurchasingDetailBO> listDetail(CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param casualtyPurchasingDetailTO 团体意外险购买详情
     * @return class CasualtyPurchasingDetailBO
     */
    default CasualtyPurchasingDetailBO addDetail(CasualtyPurchasingDetailTO casualtyPurchasingDetailTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param casualtyPurchasingDetailTO 团体意外险购买详情
     * @return class CasualtyPurchasingDetailBO
     */
    default CasualtyPurchasingDetailBO editDetail(CasualtyPurchasingDetailTO casualtyPurchasingDetailTO) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 导入
     *
     * @param casualtyPurchasingDetailTOS 团体意外险购买详情
     */
    void importExcel(List<CasualtyPurchasingDetailTO> casualtyPurchasingDetailTOS) throws SerException;

    /**
     * 获取除了已存在员工的员工编号
     *
     * @return
     * @throws SerException
     */
    default List<String> findEmpNo() throws SerException {
        return null;
    }

    /**
     * 根据员工编号获取信息
     *
     * @return
     * @throws SerException
     */
    default CasualtyPurchasingDetailBO findByEmpNo(String empNum) throws SerException {
        return null;
    }

    /**
     * 定时每天都检测一下入职时间是否是当天来改变购买状态
     *
     * @throws SerException
     */
    default void checkStatus() throws SerException {
        return;
    }
    /**
     * 商业保险管理日汇总
     * @param summationDate 时间
     * @return class SummaryBO
     * @throws SerException
     */
    default List<SummaryBO> summaDay(String summationDate) throws SerException{
        return null;
    }
    /**
     * 商业保险管理周汇总
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class SummaryBO
     * @throws SerException
     */
    default List<SummaryBO> summaWeek(Integer year,Integer month,Integer week) throws SerException{
        return null;
    }
    /**
     * 商业保险管理月汇总
     * @param year 年份
     * @param month 月份
     * @return class SummaryBO
     * @throws SerException
     */
    default List<SummaryBO> summaMonth(Integer year,Integer month) throws SerException{
        return null;
    }
    /**
     * 商业保险管理累计汇总
     * @param endDate 截止日期
     * @return class SummaryBO
     * @throws SerException
     */
    default List<SummaryBO> summaTotal(String endDate) throws SerException{
        return null;
    }
}