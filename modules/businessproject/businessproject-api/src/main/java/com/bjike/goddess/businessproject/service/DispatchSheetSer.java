package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.entity.DispatchSheet;
import com.bjike.goddess.businessproject.excel.DispatchSheetExcel;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;
import java.util.Set;

/**
 * 商务项目派工单信息管理业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DispatchSheetSer extends Ser<DispatchSheet, DispatchSheetDTO> {



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
     * 派工单信息列表总条数
     */
    default Long countDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取派工单信息列表
     *
     * @return class DispatchSheetBO
     */
    default DispatchSheetBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 派工单信息列表
     *
     * @return class DispatchSheetBO
     */
    default List<DispatchSheetBO> listDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param dispatchSheetTO 派工单信息
     * @return class DispatchSheetBO
     */
    default DispatchSheetBO addDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param dispatchSheetTO 派工单信息
     * @return class DispatchSheetBO
     */
    default DispatchSheetBO editDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteDispatchSheet(String id) throws SerException {
        return;
    }

    ;

    /**
     * 搜索
     *
     * @param dispatchSheetDTO 搜索
     * @return class DispatchSheetBO
     */
    default List<DispatchSheetBO> searchDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        return null;
    }

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> listArea() throws SerException {
        return null;
    }

    /**
     * 获取派工单名称
     *
     * @return class String
     */
    default List<String> listDispatchName() throws SerException {
        return null;
    }

    /**
     * 查找所有内部项目名称
     * chenjunhao
     *
     * @return
     * @throws SerException
     */
    Set<String> allInnerProjects() throws SerException;

    /**
     * 导出excel
     * chenjunhao
     *
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] exportExcel(DispatchSheetDTO dto) throws SerException;

    /**
     * 导入excel
     * chenjunhao
     *
     * @param toList
     * @throws SerException
     */
    void leadExcel(List<DispatchSheetTO> toList) throws SerException;
}