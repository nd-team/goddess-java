package com.bjike.goddess.workhandover.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import com.bjike.goddess.workhandover.bo.TransInfoBO;
import com.bjike.goddess.workhandover.entity.TransInfo;
import com.bjike.goddess.workhandover.dto.TransInfoDTO;
import com.bjike.goddess.workhandover.to.TransInfoTO;

import java.util.List;

/**
 * 工作交接业务接口
 *
 * @Author: [ chenyang ]
 * @Date: [ 2017-11-10 05:08 ]
 * @Description: [ 工作交接业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TransInfoSer extends Ser<TransInfo, TransInfoDTO> {

    /**
     * @描述 工作交接列表
     * @参数 [dto]
     * @返回值 java.util.List<com.bjike.goddess.workhandover.bo.TransInfoBO>
     * @创建人 chenyang
     * @创建时间 17-11-10
     * @修改人和其它信息
     */
    default List<TransInfoBO> findWorkHandOver(TransInfoDTO dto) throws SerException {
        return null;
    }

    /**
     * @描述 获取一条工作交接
     * @参数 [id]
     * @返回值 com.bjike.goddess.workhandover.bo.TransInfoBO
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default TransInfoBO getOneWorkHandOver(String id) throws SerException {

        return null;
    }

    /**
     * @描述 添加工作交接
     * @参数 [to]
     * @返回值 com.bjike.goddess.workhandover.bo.TransInfoBO
     * @创建人 chenyang
     * @创建时间 17-11-10
     * @修改人和其它信息
     */
    default TransInfoBO insertWorkHandOver(TransInfoTO to) throws SerException {
        return null;
    }

    /**
     * @描述 编辑工作交接
     * @参数 [to]
     * @返回值 com.bjike.goddess.workhandover.bo.TransInfoBO
     * @创建人 chenyang
     * @创建时间 17-11-10
     * @修改人和其它信息
     */
    default TransInfoBO editWorkHandOver(TransInfoTO to) throws SerException {

        return null;
    }

    /**
     * @描述 根据id删除工作交接
     * @参数 [id]
     * @返回值 void
     * @创建人 chenyang
     * @创建时间 17-11-10
     * @修改人和其它信息
     */
    default void removeWorkHandOver(String id) throws SerException {


    }

    /**
     * @描述 工作交接总条数
     * @参数 [dto]
     * @返回值 java.lang.Long
     * @创建人 chenyang
     * @创建时间 17-11-11
     * @修改人和其它信息
     */
    default Long countWorkHandOver(TransInfoDTO dto) throws SerException {

        return null;
    }

    /**
     *@描述   搜索工作交接
     *@参数  [to]
     *@返回值  java.util.List<com.bjike.goddess.workhandover.bo.TransInfoBO>
     *@创建人  chenyang
     *@创建时间  17-11-11
     *@修改人和其它信息
     *//*
    default List<TransInfoBO> searchWorkHandOver(TransInfoTO to) throws SerException {

        return null;
    }*/

    /**
     *@描述   审核
     *@参数  [fundEntryTO]
     *@返回值  com.bjike.goddess.workhandover.bo.TransInfoBO
     *@创建人  chenyang
     *@创建时间  17-11-11
     *@修改人和其它信息
     */
    default TransInfoBO auditWorkHandOver(TransInfoTO to) throws SerException {

        return null;
    }

    /**
     *@描述   导入
     *@参数  [to]
     *@返回值  com.bjike.goddess.workhandover.bo.TransInfoBO
     *@创建人  chenyang
     *@创建时间  17-11-11
     *@修改人和其它信息
     */
    default TransInfoBO importExcel(List<TransInfoTO> to) throws SerException {

        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(TransInfoDTO dto) throws SerException;

    /**
     * 导出Excel导入模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

}