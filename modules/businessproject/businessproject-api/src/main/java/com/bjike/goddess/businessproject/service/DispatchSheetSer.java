package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.entity.DispatchSheet;
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
     * 查找所有派工单编号
     *
     * @return
     * @throws SerException
     */
    List<String> allDispatchNum() throws SerException;

    /**
     * 根据派工单编号获取派工信息
     *
     * @param dispatchNum 派工单编号
     * @return calss DispatchSheetBO
     * @throws SerException
     */
    List<DispatchSheetBO> getInfoByDispatchNum(String dispatchNum) throws SerException;

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

    /**
     * 导出excel模板
     *
     * @return
     * @throws SerException
     */
    byte[] templateExcel() throws SerException;

    /**
     * chenjunhao
     * 获取所有派工单号
     *
     * @return
     * @throws SerException
     */
    Set<String> nums() throws SerException;

    /**
     * 获取所有地区
     *
     * @return
     * @throws SerException
     */
    Set<String> areas() throws SerException;
    /**
     * 根据地区获取所属项目组
     *
     * @return
     * @throws SerException
     */
    List<String> getProjectGroup(String area) throws SerException;
    /**
     * 根据地区,所属项目组获取内部项目名称
     *
     * @return
     * @throws SerException
     */
    List<String> getInnerName(String area,String projectGroup) throws SerException;
}