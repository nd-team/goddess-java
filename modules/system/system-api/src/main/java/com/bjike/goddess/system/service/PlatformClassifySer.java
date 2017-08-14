package com.bjike.goddess.system.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.system.bo.PlatformClassifyBO;
import com.bjike.goddess.system.dto.PlatformClassifyDTO;
import com.bjike.goddess.system.entity.PlatformClassify;
import com.bjike.goddess.system.excel.SonPermissionObject;
import com.bjike.goddess.system.to.GuidePermissionTO;
import com.bjike.goddess.system.to.PlatformClassifyTO;

import java.util.List;

/**
 * 平台分类业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 01:44 ]
 * @Description: [ 平台分类业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PlatformClassifySer extends Ser<PlatformClassify, PlatformClassifyDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 平台分类列表总条数
     */
    default Long count(PlatformClassifyDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个平台分类
     *
     * @return class PlatformClassifyBO
     */
    default PlatformClassifyBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 平台分类
     *
     * @param dto 平台分类dto
     * @return class PlatformClassifyBO
     * @throws SerException
     */
    default List<PlatformClassifyBO> list(PlatformClassifyDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加平台分类
     *
     * @param to 平台分类数据to
     * @return class PlatformClassifyBO
     * @throws SerException
     */
    default PlatformClassifyBO insert(PlatformClassifyTO to) throws SerException {
        return null;
    }

    /**
     * 编辑平台分类
     *
     * @param to 平台分类数据to
     * @return class PlatformClassifyBO
     * @throws SerException
     */
    default PlatformClassifyBO edit(PlatformClassifyTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除平台分类
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 导入
     *
     * @param platformClassifyTOS 平台分类
     * @return class PlatformClassifyBO
     */
    default PlatformClassifyBO importExcel(List<PlatformClassifyTO> platformClassifyTOS) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(PlatformClassifyDTO dto) throws SerException;

    /**
     * Excel下载模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;
}