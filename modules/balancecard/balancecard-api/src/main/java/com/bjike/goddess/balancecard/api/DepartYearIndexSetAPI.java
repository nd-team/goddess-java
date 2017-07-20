package com.bjike.goddess.balancecard.api;

import com.bjike.goddess.balancecard.bo.DepartYearIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartYearIndexSetDTO;
import com.bjike.goddess.balancecard.entity.DepartYearIndexSet;
import com.bjike.goddess.balancecard.to.DepartYearIndexSetTO;
import com.bjike.goddess.balancecard.to.ExportExcelDepartTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 部门年度指标设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:28 ]
 * @Description: [ 部门年度指标设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DepartYearIndexSetAPI {


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
     * 年度指标列表总条数
     */
    default Long countDepartYearIndexSet(DepartYearIndexSetDTO departYearIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 年度指标列表id
     * @return class DepartYearIndexSetBO
     */
    default DepartYearIndexSetBO getOneById (String id) throws SerException {return null;}


    /**
     * 年度指标列表
     *
     * @return class DepartYearIndexSetBO
     */
    default List<DepartYearIndexSetBO> listDepartYearIndexSet(DepartYearIndexSetDTO departYearIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param departYearIndexSetTO 年度指标信息
     * @return class DepartYearIndexSetBO
     */
    default DepartYearIndexSetBO addDepartYearIndexSet(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param departYearIndexSetTO 年度指标信息
     * @return class DepartYearIndexSetBO
     */
    default DepartYearIndexSetBO editDepartYearIndexSet(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteDepartYearIndexSet(String id) throws SerException {
        return;
    }

    ;

    /**
     * 分解部门月度指标
     *
     * @param departYearIndexSetTO 年度指标信息
     * @return class DepartYearIndexSetBO
     */
    default DepartYearIndexSetBO seperateDepartYear(DepartYearIndexSetTO departYearIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 部门年度报告导出Excel
     *
     * @param to
     * @throws SerException
     */
    default byte[] departYearReport(ExportExcelDepartTO to) throws SerException{
        return null;
    };

    /**
     * 获取所有地区
     *
     * @throws SerException
     */
    default List<String> listArea(   ) throws SerException{
        return null;
    };
    /**
     * 获取所有项目组部门
     *
     * @throws SerException
     */
    default List<String> listDepart(   ) throws SerException{
        return null;
    };
    /**
     * 获取所有员工
     *
     * @throws SerException
     */
    default List<String> listEmp(   ) throws SerException{
        return null;
    };


    /**
     * 导入Excel
     *
     * @param toList
     * @throws SerException
     */
    void leadExcel(List<DepartYearIndexSetTO> toList) throws SerException;

    /**
     * 部门年度报告导出Excel
     *
     * @param to
     * @throws SerException
     */
    byte[] exportExcel(ExportExcelDepartTO to) throws SerException;


    /**
     * 根据年度指标id 来查询部门年度信息
     * @param
     *
     */

    default List<DepartYearIndexSetBO> dendrogram(String id) throws SerException{
        return null;
    }


}