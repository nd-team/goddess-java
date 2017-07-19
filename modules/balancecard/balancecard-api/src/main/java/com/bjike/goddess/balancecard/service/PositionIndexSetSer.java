package com.bjike.goddess.balancecard.service;

import com.bjike.goddess.balancecard.bo.PositionIndexSetBO;
import com.bjike.goddess.balancecard.excel.SonPermissionObject;
import com.bjike.goddess.balancecard.to.ExportExcelPositTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.PositionIndexSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.balancecard.dto.PositionIndexSetDTO;

import java.util.List;

/**
 * 岗位指标设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:38 ]
 * @Description: [ 岗位指标设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PositionIndexSetSer extends Ser<PositionIndexSet, PositionIndexSetDTO> {


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
     * 岗位指标列表总条数
     */
    default Long countPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 岗位指标列表id
     *
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 岗位指标列表(包括岗位个人查询)
     *
     * @return class PositionIndexSetBO
     */
    default List<PositionIndexSetBO> listPositionIndexSet(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param positionIndexSetTO 岗位指标信息
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO addPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param positionIndexSetTO 岗位指标信息
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO editPositionIndexSet(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deletePositionIndexSet(String id) throws SerException {
        return;
    }

    ;

    /**
     * 本月岗位指标列表总条数
     */
    default Long countNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 本月岗位指标列表
     *
     * @return class PositionIndexSetBO
     */
    default List<PositionIndexSetBO> listNow(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }


    /**
     * 我的指标列表总条数
     */
    default Long countSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }


    /**
     * 我的指标列表
     *
     * @return class PositionIndexSetBO
     */
    default List<PositionIndexSetBO> listSelf(PositionIndexSetDTO positionIndexSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加我的指标
     *
     * @param positionIndexSetTO 岗位指标信息
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO addSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return null;

    }

    /**
     * 编辑我的指标
     *
     * @param positionIndexSetTO 岗位指标信息
     * @return class PositionIndexSetBO
     */
    default PositionIndexSetBO editSelf(PositionIndexSetTO positionIndexSetTO) throws SerException {
        return null;
    }

    /**
     * 删除我的指标
     *
     * @param id id
     */
    default void deleteSelf(String id) throws SerException {
        return;
    }

    ;

    /**
     * 岗位报告导入 Excel
     *
     * @param toList
     * @throws SerException
     */
    void leadExcel(List<PositionIndexSetTO> toList) throws SerException;

    /**
     * 岗位报告导出Excel
     *
     * @param to
     * @throws SerException
     */
    default byte[] positionReport(ExportExcelPositTO to) throws SerException {
        return null;
    }

    ;

    /**
     * 个人报告导出Excel
     *
     * @param to
     * @throws SerException
     */
    default byte[] personReport(ExportExcelPositTO to) throws SerException {
        return null;
    }

    ;

    /**
     * 根据部门月度传来的id来查询它所分解的岗位指标
     * @param id
     * @throws SerException
     */
    default List<PositionIndexSetBO> dendrogram(String id ) throws SerException{
        return null;
    }


}