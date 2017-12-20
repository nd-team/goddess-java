package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.SummationBO;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.bo.SupplierInfoRegistraDataBO;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.entity.SupplierInfo;
import com.bjike.goddess.supplier.to.SupplierInfoRegistraDataTO;
import com.bjike.goddess.supplier.to.SupplierInfoTO;

import java.util.List;

/**
 * 供应商信息管理业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupplierInfoSer extends Ser<SupplierInfo, SupplierInfoDTO> {
    /**
     * 供应商信息管理总条数
     */
    default Long countSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取供应商信息管理
     *
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 供应商信息管理列表
     *
     * @return class SupplierInfoBO
     */
    default List<SupplierInfoBO> listSupplierInfo(SupplierInfoDTO headersCustomDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param supplierInfoTO 供应商信息管理
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO addSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param supplierInfoTO 供应商信息管理
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO editSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSupplierInfo(String id) throws SerException {
        return;
    }
    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     *  导入
     * @param supplierInfoTOList 工龄补助
     */
    void importExcel(List<SupplierInfoTO> supplierInfoTOList) throws SerException;

    /**
     * 供应商信息详情获取数据
     *
     * @param id 供应商信息管理id
     * @return class SupplierInfoRegistraDataBO
     */
    default SupplierInfoRegistraDataBO linkSupplierData(String id) throws SerException {
        return null;
    }

    /**
     * 供应商信息详情编辑
     *
     * @param supplierInfoRegistraDataTO 供应商信息详情
     */
    default void addSupplierDetail(SupplierInfoRegistraDataTO supplierInfoRegistraDataTO) throws SerException {
        return;
    }
    /**
     * 供应商信息日汇总
     *
     * @param summDate 日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaDay(String summDate) throws SerException {
        return null;
    }

    /**
     * 供应商信息周汇总
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
     * 供应商信息月汇总
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
     * 供应商信息季度汇总
     *
     * @param quarter 季度
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaQuarter(Integer year,Integer quarter) throws SerException {
        return null;
    }


    /**
     * 供应商信息年度汇总
     *
     * @param year 年度
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaYear(Integer year) throws SerException {
        return null;
    }


    /**
     * 供应商信息累计汇总
     *
     * @param endDate 截止日期
     * @return class SummationBO
     * @throws SerException
     */
    default List<SummationBO> summaTotal(String endDate) throws SerException {
        return null;
    }
}