package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.DepartMonIndexSetBO;
import com.bjike.goddess.balancecard.excel.SonPermissionObject;
import com.bjike.goddess.balancecard.to.DepartMonIndexSetTO;
import com.bjike.goddess.balancecard.to.ExportExcelDepartTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.balancecard.entity.DepartMonIndexSet;
import com.bjike.goddess.balancecard.dto.DepartMonIndexSetDTO;

import java.util.List;

/**
 * 部门月度指标设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 部门月度指标设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DepartMonIndexSetSer extends Ser<DepartMonIndexSet, DepartMonIndexSetDTO> {


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
     * 月度指标列表总条数
     */
    default Long countDepartMonIndexSet(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 月度指标列表id
     *
     * @return class DepartMonIndexSetBO
     */
    default DepartMonIndexSetBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 月度指标列表
     *
     * @return class DepartMonIndexSetBO
     */
    default List<DepartMonIndexSetBO> listDepartMonIndexSet(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param departMonIndexSetTO 月度指标信息
     * @return class DepartMonIndexSetBO
     */
    default DepartMonIndexSetBO addDepartMonIndexSet(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param departMonIndexSetTO 月度指标信息
     * @return class DepartMonIndexSetBO
     */
    default DepartMonIndexSetBO editDepartMonIndexSet(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteDepartMonIndexSet(String id) throws SerException {
        return;
    }

    ;

    /**
     * 分解岗位指标
     *
     * @param departMonIndexSetTO 月度指标信息
     * @return class DepartMonIndexSetBO
     */
    default DepartMonIndexSetBO seperateDepartYear(DepartMonIndexSetTO departMonIndexSetTO) throws SerException {
        return null;
    }


    /**
     * 查看本月月度指标列表总条数
     */
    default Long countNow(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        return null;
    }


    /**
     * 查看本月月度指标列表
     *
     * @return class DepartMonIndexSetBO
     */
    default List<DepartMonIndexSetBO> listNow(DepartMonIndexSetDTO departMonIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 导入Excel
     *
     * @param toList
     * @throws SerException
     */
    void leadExcel(List<DepartMonIndexSetTO> toList) throws SerException;

    /**
     * 部门月度报告导出Excel
     *
     * @param to
     * @throws SerException
     */
    default byte[] exportExcel(ExportExcelDepartTO to) throws SerException {
        return null;
    }

    ;

    /**
     * 根据部门年度指标传过来的id来查询它所分解而来的部门月度指标
     *@param  id
     * @return class DepartMonIndexSetBO
     */
    default List<DepartMonIndexSetBO> dendrogram(String id) throws SerException {
        return null;
    }

    /**
     * 导出Excel导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;




}