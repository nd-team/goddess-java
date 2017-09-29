package com.bjike.goddess.businesscommission.api;

import com.bjike.goddess.businesscommission.bo.ProportionBO;
import com.bjike.goddess.businesscommission.dto.ProportionDTO;
import com.bjike.goddess.businesscommission.dto.WeightAllotDTO;
import com.bjike.goddess.businesscommission.excel.SonPermissionObject;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.ProportionExcelTO;
import com.bjike.goddess.businesscommission.to.ProportionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 业务提成分配比例表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:29 ]
 * @Description: [ 业务提成分配比例表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProportionAPI {

    /**
     * 列表总条数
     *
     * @param proportionDTO
     * @return
     * @throws SerException
     */
    default Long getTotal(ProportionDTO proportionDTO) throws SerException {
        return null;
    }

    /**
     * 一个业务提成分配比例
     *
     * @param id
     * @return
     * @throws SerException
     */
    default ProportionBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 项目业务提成分配比例列表
     *
     * @param proportionDTO
     * @return
     * @throws SerException
     */
    default List<ProportionBO> listProportion(ProportionDTO proportionDTO) throws SerException {
        return null;
    }

    /**
     * 添加项目业务提成分配比例
     *
     * @param proportionTO
     * @return
     * @throws SerException
     */
    default void addProportion(ProportionTO proportionTO) throws SerException {
        return;
    }

    /**
     * 编辑项目业务提成分配比例
     *
     * @param proportionTO
     * @return
     * @throws SerException
     */
    default void editProportion(ProportionTO proportionTO) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void deleteProportion(String id) throws SerException {
        return;
    }

    /**
     * 确认
     *
     * @param tar
     * @throws SerException
     */
    default void confirm(Boolean tar) throws SerException {
        return;
    }

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
     * 导入
     *
     * @param tos 业务提成分配比例信息
     */
    default void importExcel(List<ProportionExcelTO> tos) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    default byte[] exportExcel(ProportionDTO dto) throws SerException{
        return null;
    }

    /**
     * 导出Excel导入模板
     *
     * @throws SerException
     */
    default byte[] templateExport() throws SerException{
        return null;
    }
}