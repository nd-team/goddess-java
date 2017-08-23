package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.YearIndexSetBO;
import com.bjike.goddess.balancecard.dto.YearIndexSetDTO;
import com.bjike.goddess.balancecard.excel.SonPermissionObject;
import com.bjike.goddess.balancecard.to.*;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 年度指标设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 年度指标设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface YearIndexSetAPI {


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
     * 年度指标列表总条数
     */
    default Long countYearIndexSet(YearIndexSetDTO yearIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 年度指标列表id
     *
     * @return class YearIndexSetBO
     */
    default YearIndexSetBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 年度指标列表
     *
     * @return class YearIndexSetBO
     */
    default List<YearIndexSetBO> listYearIndexSet(YearIndexSetDTO yearIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param yearIndexSetTO 年度指标信息
     * @return class YearIndexSetBO
     */
    default YearIndexSetBO addYearIndexSet(YearIndexSetTO yearIndexSetTO) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param yearIndexSetTO 年度指标信息
     * @return class YearIndexSetBO
     */
    default YearIndexSetBO editYearIndexSet(YearIndexSetTO yearIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteYearIndexSet(String id) throws SerException {
        return;
    }

    ;

    /**
     * 分解部门年度指标
     *
     * @param yearIndexSetTO 年度指标信息
     * @return class YearIndexSetBO
     */
    default YearIndexSetBO seperateDepartYear(YearIndexSetTO yearIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 获取所有年份
     */
    default List<String> yearList() throws SerException {

        return null;
    }

    /**
     * 导入Excel
     *
     * @param toList
     * @throws SerException
     */
    void leadExcel(List<YearIndexSetTO> toList) throws SerException;

    /**
     * 导出Excel
     *
     * @param to
     * @throws SerException
     */
    byte[] exportExcel(ExportExcelYearTO to) throws SerException;

    /**
     * 年度报告导出Excel
     *
     * @param to
     * @throws SerException
     */
    byte[] exportYearExcel(ExportExcelYearTO to) throws SerException;

    /**
     * 维度和指标类型报告导出Excel
     *
     * @param to
     * @throws SerException
     */
    byte[] exportYearDeExcel(ExportExcelYearTO to) throws SerException;

    /**
     * 树状图查询方法
     *
     * @param yearIndexSetDTO
     * @throws SerException
     */
    default List<YearIndexSetBO> dendrogram(YearIndexSetDTO yearIndexSetDTO) throws SerException{
        return null;
    }

    /**
     * 导出Excel导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;


}