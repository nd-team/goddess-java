package com.bjike.goddess.managefee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.managefee.bo.ManageFeeBO;
import com.bjike.goddess.managefee.entity.ManageFee;
import com.bjike.goddess.managefee.dto.ManageFeeDTO;
import com.bjike.goddess.managefee.to.*;

import java.util.List;

/**
 * 管理费业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ManageFeeSer extends Ser<ManageFee, ManageFeeDTO> {

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
     * 管理费列表总条数
     */
    default Long countManageFee(ManageFeeDTO manageFeeDTO) throws SerException {
        return null;
    }



    /**
     * 管理费列表id
     * @return class ManageFeeBO
     */
    default ManageFeeBO getOneById (String id) throws SerException {return null;}


    /**
     * 管理费列表
     *
     * @return class ManageFeeBO
     */
    default List<ManageFeeBO> listManageFee(ManageFeeDTO manageFeeDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param manageFeeTO 管理费信息
     * @return class ManageFeeBO
     */
    default ManageFeeBO addManageFee(ManageFeeTO manageFeeTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param manageFeeTO 管理费信息
     * @return class ManageFeeBO
     */
    default ManageFeeBO editManageFee(ManageFeeTO manageFeeTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteManageFee(String id) throws SerException {
        return;
    }

    ;


    /**
     * 根据地区汇总详细
     *
     * @param  collectAreaTO
     */
    default List<ManageFeeBO> collectAreaDetial(CollectAreaTO collectAreaTO) throws SerException {

        return null;
    }

    ;

    /**
     * 根据地区汇总
     *
     * @param collectAreaTO
     */
    default List<ManageFeeBO> collectArea(CollectAreaTO collectAreaTO) throws SerException {

        return null;
    }

    ;
    /**
     * 根据项目组汇总
     *
     * @param collectGroupTO
     */
    default List<ManageFeeBO> collectGroup(CollectGroupTO collectGroupTO) throws SerException {

        return null;
    }

    ;/**
     * 根据项目组汇总详细
     *
     * @param collectGroupTO
     */
    default List<ManageFeeBO> collectGroupDetail(CollectGroupTO collectGroupTO) throws SerException {

        return null;
    }

    ;
    /**
     * 根据项目汇总
     *
     * @param collectProjectTO
     */
    default List<ManageFeeBO> collectProject(CollectProjectTO collectProjectTO) throws SerException {

        return null;
    }
    /**
     * 根据项目汇总详细
     *
     * @param collectProjectTO
     */
    default List<ManageFeeBO> collectProjectDetail(CollectProjectTO collectProjectTO) throws SerException {

        return null;
    }

    ;
    /**
     * 根据类别汇总
     *
     * @param collectCategoryTO
     */
    default List<ManageFeeBO> collectType(CollectCategoryTO collectCategoryTO) throws SerException {

        return null;
    }

    ;/**
     * 根据类别汇总详细
     *
     * @param collectCategoryTO
     */
    default List<ManageFeeBO> collectTypeDetail(CollectCategoryTO collectCategoryTO) throws SerException {

        return null;
    }

    ;


    /**
     * 获取所有年份
     *
     */
    default List<String> yearList( ) throws SerException {

        return null;
    }

    ;
    /**
     * 获取所有地区
     *
     */
    default List<String> areaList( ) throws SerException {

        return null;
    }

    ;/**
     * 获取所有项目组
     *
     */
    default List<String> groupList( ) throws SerException {

        return null;
    }

    ;/**
     * 获取所有项目
     *
     */
    default List<String> projectList( ) throws SerException {

        return null;
    }

    ;


    /**
     * 获取所有类别
     */
    default List<String> typeList() throws SerException {

        return null;
    }

    ;


    /**
     * 地区汇总导出Excel
     * @param collectAreaTO
     * @throws SerException
     */
    byte[] areaExportReport(CollectAreaTO collectAreaTO ) throws SerException;



    /**
     * 项目名称汇总导出Excel
     * @param collectProjectTO
     * @throws SerException
     */
    byte[] projectExportReport(CollectProjectTO collectProjectTO ) throws SerException;



    /**
     * 项目组汇总导出Excel
     * @param collectProjectTO
     * @throws SerException
     */
    byte[] groupExportReport( CollectGroupTO collectProjectTO ) throws SerException;



    /**
     * 类别汇总导出Excel
     * @param collectCategoryTO
     * @throws SerException
     */
    byte[] typeExportReport(CollectCategoryTO collectCategoryTO ) throws SerException;

    /**
     * 导入
     *
     * @param manageFeeTOS 管理费导入数据
     * @return class ManageFeeBO
     */
    default ManageFeeBO importExcel(List<ManageFeeTO> manageFeeTOS) throws SerException {
        return null;
    }

    /**
     * 导出Excel导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;




}