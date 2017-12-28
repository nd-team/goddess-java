package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.*;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 客户基本信息业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.056 ]
 * @Description: [ 客户基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerBaseInfoAPI {


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
     * 自动生成一个客户编号
     *
     * @return class CustomerBaseInfoBO
     */
    default CustomerBaseInfoBO generateCustomerNum() throws SerException {
        return null;
    }

    /**
     * 客户基本信息列表总条数
     */
    default Long countCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        return null;
    }

    /**
     * 客户基本信息列表
     *
     * @return class CustomerBaseInfoBO
     */
    default List<CustomerBaseInfoBO> listCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param customerBaseInfoTO 客户基本信息信息
     * @return class CustomerBaseInfoBO
     */
    default CustomerBaseInfoBO addCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param customerBaseInfoTO 客户基本信息信息
     * @return class CustomerBaseInfoBO
     */
    default CustomerBaseInfoBO editCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        return null;
    }

    /**
     * 删除基本信息
     *
     * @param id id
     */
    default void deleteCustomerBaseInfo(String id) throws SerException {
        return;
    }

    ;

    /**
     * 冻结客户基本信息
     *
     * @param id id
     */
    default void congealCustomerBaseInfo(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻客户基本信息
     *
     * @param id id
     */
    default void thawCustomerBaseInfo(String id) throws SerException {
        return;
    }

    ;

    /**
     * 获取所有编号
     *
     * @return class String
     */
    default List<String> getCustomerBaseInfoCusNum() throws SerException {
        return null;
    }


    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getCustomerBaseInfoArea() throws SerException {
        return null;
    }

    /**
     * 获取客户名
     *
     * @return class String
     */
    default List<String> getCustomerBaseInfoName() throws SerException {
        return null;
    }


    /**
     * 添加市场开发添加的客户
     *
     * @param customerName 客户名
     * @return class CustomerBaseInfoBO
     */
    default CustomerBaseInfoBO addMarketCustomerInfo(@NotBlank String customerName, String origanizion) throws SerException {
        return null;
    }

    /**
     * 根据客户编号查询客户
     *
     * @param customerNum 客户编号
     * @return class CustomerBaseInfoBO
     */
    default CustomerBaseInfoBO getCustomerInfoByNum(String customerNum) throws SerException {
        return null;
    }

    /**
     * 获取行业数组
     *
     * @return class String
     */
    default List<String> getCustomerBaseInfoWorks() throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 通过组织机构名称查找
     *
     * @param origanizion 组织机构名称
     * @return
     * @throws SerException
     */
    List<CustomerBaseInfoBO> findByOriganizion(String origanizion) throws SerException;

    /**
     * lijuntao
     * 获取用户名和用户编号
     *
     * @return
     * @throws SerException
     */
    List<CustomerNameNumBO> findNameNum() throws SerException;

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
     * 客户信息管理日汇总
     *
     * @param summDate 日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaDay(String summDate) throws SerException {
        return null;
    }

    /**
     * 客户信息管理周汇总
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
     * 客户信息管理月汇总
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
     * 客户信息管理季度汇总
     *
     * @param quarter 季度
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        return null;
    }


    /**
     * 客户信息管理年度汇总
     *
     * @param year 年度
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaYear(Integer year) throws SerException {
        return null;
    }


    /**
     * 客户信息管理累计汇总
     *
     * @param endDate 截止日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaTotal(String endDate) throws SerException {
        return null;
    }

    /**
     * 客户信息管理图形展示日汇总数据
     *
     * @param summDate 日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowDay(String summDate) throws SerException {
        return null;
    }

    /**
     * 客户信息管理图形展示周汇总数据
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 客户信息管理图形展示月汇总数据
     *
     * @param year  年份
     * @param month 月份
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 客户信息管理图形展示季度汇总数据
     *
     * @param year    年份
     * @param quarter 季度
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        return null;
    }

    /**
     * 客户信息管理图形展示年度汇总数据
     *
     * @param year 年份
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 客户信息管理图形展示累计汇总数据
     *
     * @param endDate 截止日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowTotal(String endDate) throws SerException {
        return null;
    }

    /**
     * 客户地区分布情况
     *
     * @return
     * @throws SerException
     */
    default PieOptionBO areaPieShow() throws SerException {
        return null;
    }

    /**
     * 客户类型分类分析
     *
     * @param area 地区
     * @return
     * @throws SerException
     */
    default PieOptionBO areaBussTypePieShow(String area) throws SerException {
        return null;
    }

    /**
     * 获取所有的地区
     *
     * @return
     * @throws SerException
     */
    default List<String> findArea() throws SerException {
        return null;
    }

    /**
     * 各业务类型客户地区分布情况
     *
     * @return
     * @throws SerException
     */
    default OptionBO bussTypeAreaBaiShow() throws SerException {
        return null;
    }

    /**
     * 获取所有的业务类型
     */
    default List<String> findBussType() throws SerException {
        return null;
    }

    /**
     * 客户来源分析
     *
     * @return
     * @throws SerException
     */
    default OptionBO resouceBaiShow() throws SerException {
        return null;
    }

    /**
     * 根据业务类型客户来源分析
     *
     * @return
     * @throws SerException
     */
    default PieOptionBO resoucePieShowBybussType(String bussType) throws SerException {
        return null;
    }

    /**
     * 将所有数据重新计算一遍
     *
     * @return
     * @throws SerException
     */
    default List<CustomerBaseInfoBO> computations() throws SerException {
        return null;
    }

    /**
     * 根据客户编号查询公司名称,职务,姓名,电话
     */
    default CustomerInfoBO findByNum(String customerNum) throws SerException {
        return null;
    }

    /**
     * 获取客户编号
     *
     * @return
     * @throws SerException
     */
    default List<String> findCode() throws SerException {
        return null;
    }
    /**
     * 客户管理平台业主客户列表
     *
     * @param dto
     * @return class CustomerAndOwnerInfoBO
     * @throws SerException
     */
    List<CustomerAndOwnerInfoBO> customerList(CustomerBaseInfoDTO dto) throws SerException;

    /**
     * 客户管理平台业主客户id
     *
     * @param id
     * @return
     * @throws SerException
     */
    CustomerAndOwnerInfoBO customerById(String id) throws SerException;
}