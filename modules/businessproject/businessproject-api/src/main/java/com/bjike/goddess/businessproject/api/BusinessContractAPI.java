package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.*;
import com.bjike.goddess.businessproject.dto.BusinessContractDTO;
import com.bjike.goddess.businessproject.to.BusinessContractTO;
import com.bjike.goddess.businessproject.to.CollectUpdateTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.PersonTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;
import java.util.Set;

/**
 * 商务项目合同业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:36 ]
 * @Description: [ 商务项目合同业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessContractAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 商务项目合同列表总条数
     */
    default Long count(BusinessContractDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取商务项目合同
     *
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 商务项目合同列表
     *
     * @return class BusinessContractsBO
     */
    default List<BusinessContractsBO> list(BusinessContractDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加商务项目合同
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO add(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 编辑商务项目合同
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO edit(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 获取通过内容
     *
     * @param id 注册id
     * @return
     * @throws SerException
     */
    default String findNotisDate(String id) throws SerException {
        return null;
    }

    /**
     * 删除商务项目合同
     *
     * @param id id
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 获取所有地区
     */
    default Set<String> areas() throws SerException {
        return null;
    }

    /**
     * 项目经理意见分析
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO managerIdea(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 规划模块分析意见
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO planIdea(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 预算模块分析意见
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO budgetIdea(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 预立项
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO hadContract(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 预立项目
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO advance(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 预估项目变更
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO changes(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 通报
     *
     * @param to 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO notification(BusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 根据地区汇总商务合同管理明细汇总
     *
     * @return class BusinessContractADetailBO
     * @throws SerException
     */
    default List<BusinessContractADetailBO> collect() throws SerException {
        return null;
    }

    /**
     * 修改商务合同管理明细汇总
     *
     * @param to
     * @return
     * @throws SerException
     */
    default List<BusinessContractADetailBO> collectUpdate(CollectUpdateTO to) throws SerException {
        return null;
    }

    /**
     * 商务合同管理日汇总
     *
     * @param time
     * @return class BusinessContractProgressBO
     * @throws SerException
     */
    default List<BusinessContractProgressBO> dayCollect(String time) throws SerException {
        return null;
    }

    /**
     * 商务合同管理周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class BusinessContractProgressBO
     * @throws SerException
     */
    default List<BusinessContractProgressBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 商务合同管理月汇总
     *
     * @param year
     * @param month
     * @return class BusinessContractProgressBO
     * @throws SerException
     */
    default List<BusinessContractProgressBO> monthCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 商务合同季度汇总
     *
     * @param year
     * @param quarter
     * @return class BusinessContractProgressBO
     * @throws SerException
     */
    default List<BusinessContractProgressBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 商务合同管理年汇总
     *
     * @param year
     * @return class BusinessContractProgressBO
     * @throws SerException
     */
    default List<BusinessContractProgressBO> yearCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 商务合同管理累计汇总
     *
     * @param time
     * @return class BusinessContractProgressBO
     * @throws SerException
     */
    default List<BusinessContractProgressBO> totalCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各地区合同规模数图表日汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayAreaScaleFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各地区合同规模数图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekAreaScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各地区合同规模数图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthAreaScaleFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各地区合同规模数图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO quarterAreaScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各地区合同规模数图表年汇总
     *
     * @param year
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO yearAreaScaleFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各地区合同规模数图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalAreaScaleFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同规模数图表日汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayProjectGroupScaleFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同规模数图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekProjectGroupScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同规模数图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthProjectGroupScaleFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同规模数图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO quarterProjectGroupScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同规模数图表年份汇总
     *
     * @param year
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO yearProjectGroupScaleFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同规模数图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalProjectGroupScaleFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同规模数图表日汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayMajorScaleFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同规模数图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekMajorScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同规模数图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthMajorScaleFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同规模数图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO quarterMajorScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同规模数图表年份汇总
     *
     * @param year
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO yearMajorScaleFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同规模数图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalMajorScaleFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同规模数图表日汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayCompanyScaleFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同规模数图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekCompanyScaleFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同规模数图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthCompanyScaleFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同规模数图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO quarterCompanyScaleFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同规模数图表年份汇总
     *
     * @param year
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO yearCompanyScaleFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同规模数图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalCompanyScaleFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况金额图表日汇总
     *
     * @param time
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO dayAreaMakeFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况金额图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO weekAreaMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况金额图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO monthAreaMakeFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况金额图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO quarterAreaMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况金额图表年汇总
     *
     * @param year
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO yearAreaMakeFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况金额图表累计汇总
     *
     * @param time
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO totalAreaMakeFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况金额图表日汇总
     *
     * @param time
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO dayProjectGroupMakeFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况金额图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO weekProjectGroupMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况金额图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO monthProjectGroupMakeFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况金额图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO quarterProjectGroupMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况金额图表年汇总
     *
     * @param year
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO yearProjectGroupMakeFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况金额图表累计汇总
     *
     * @param time
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO totalProjectGroupMakeFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况金额图表日汇总
     *
     * @param time
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO dayMajorMakeFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况金额图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO weekMajorMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况金额图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO monthMajorMakeFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况金额图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO quarterMajorMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况金额图表年汇总
     *
     * @param year
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO yearMajorMakeFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况金额图表累计汇总
     *
     * @param time
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO totalMajorMakeFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况金额图表日汇总
     *
     * @param time
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO dayCompanyMakeFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况金额图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO weekCompanyMakeFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况金额图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO monthCompanyMakeFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况金额图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO quarterCompanyMakeFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况金额图表年汇总
     *
     * @param year
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO yearCompanyMakeFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况金额图表累计汇总
     *
     * @param time
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO totalCompanyMakeFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况图表日汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayAreaMakeCaseFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekAreaMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthAreaMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO quarterAreaMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况图表年汇总
     *
     * @param year
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO yearAreaMakeCaseFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各地区合同立项情况图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalAreaMakeCaseFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况图表日汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayProjectGroupMakeCaseFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekProjectGroupMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthProjectGroupMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO quarterProjectGroupMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况图表年汇总
     *
     * @param year
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO yearProjectGroupMakeCaseFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各所属项目组合同立项情况图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalProjectGroupMakeCaseFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况图表日汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayMajorMakeCaseFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekMajorMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthMajorMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO quarterMajorMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况图表年汇总
     *
     * @param year
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO yearMajorMakeCaseFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各专业工期合同立项情况图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalMajorMakeCaseFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况图表日汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO dayCompanyMakeCaseFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况图表周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO weekCompanyMakeCaseFigureCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况图表月汇总
     *
     * @param year
     * @param month
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO monthCompanyMakeCaseFigureCollect(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况图表季度汇总
     *
     * @param year
     * @param quarter
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO quarterCompanyMakeCaseFigureCollect(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况图表年汇总
     *
     * @param year
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO yearCompanyMakeCaseFigureCollect(Integer year) throws SerException {
        return null;
    }

    /**
     * 各总包单位合同立项情况图表累计汇总
     *
     * @param time
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO totalCompanyMakeCaseFigureCollect(String time) throws SerException {
        return null;
    }

    /**
     * 合同规模数汇总
     *
     * @param year
     * @return class BrokenOptionBO
     * @throws SerException
     */
    default BrokenOptionBO scaleNum(Integer year) throws SerException {
        return null;
    }

    /**
     * 立项情况金额汇总
     *
     * @param year
     * @return class BrokenOptionMakeBO
     * @throws SerException
     */
    default BrokenOptionMakeBO makeMoney(Integer year) throws SerException {
        return null;
    }

    /**
     * 立项情况汇总
     *
     * @param year
     * @return class BrokenOptionBO
     * @throws SerException
     */
    default BrokenOptionBO makeCase(Integer year) throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param contractTOS 商务项目合同
     * @return class BusinessContractsBO
     */
    default BusinessContractsBO importExcel(List<BusinessContractTO> contractTOS) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(BusinessContractDTO dto) throws SerException;

    /**
     * 导入Excel导入模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 个人图表周汇总
     *
     * @param to
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO weekPersonFigure(PersonTO to) throws SerException {
        return null;
    }

    /**
     * 个人图表月汇总
     *
     * @param to
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO monthPersonFigure(PersonTO to) throws SerException {
        return null;
    }

    /**
     * 个人图表季度汇总
     *
     * @param to
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO quarterPersonFigure(PersonTO to) throws SerException {
        return null;
    }

    /**
     * 个人图表年汇总
     *
     * @param to
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO yearPersonFigure(PersonTO to) throws SerException {
        return null;
    }

    /**
     * 部门图表周汇总
     *
     * @param to
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO weekDepartFigure(PersonTO to) throws SerException {
        return null;
    }

    /**
     * 部门图表月汇总
     *
     * @param to
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO monthDepartFigure(PersonTO to) throws SerException {
        return null;
    }

    /**
     * 部门图表季度汇总
     *
     * @param to
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO quarterDepartFigure(PersonTO to) throws SerException {
        return null;
    }

    /**
     * 部门图表年汇总
     *
     * @param to
     * @return class OptionMakeBO
     * @throws SerException
     */
    default OptionMakeBO yearDepartFigure(PersonTO to) throws SerException {
        return null;
    }

    /**
     * 获取所有的单次合同名
     * lijuntao
     *
     * @throws SerException
     */
    default List<String> findSingleContractName() throws SerException {
        return null;
    }

    /**
     * 根据单次合同名获取单次合同编号
     * lijuntao
     *
     * @param singName 单次合同名
     * @throws SerException
     */
    default List<String> findSingleNumByName(String singName) throws SerException {
        return null;
    }

    /**
     * 根据单次合同编号获取对应信息
     * lijuntao
     *
     * @param singleNum 单次合同编号
     * @return class BusinessContractsBO
     * @throws SerException
     */
    default BusinessContractsBO findBySingleNum(String singleNum) throws SerException {
        return null;
    }

    /**
     * 获取市场编号(市场信息编号)
     */
    default List<String> findMarkNum() throws SerException {
        return null;
    }

    /**
     * 根据市场编号(市场信息编号)获取
     */
}