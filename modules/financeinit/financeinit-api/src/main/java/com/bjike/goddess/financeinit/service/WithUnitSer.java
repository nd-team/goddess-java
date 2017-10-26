package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.financeinit.bo.WithUnitBO;
import com.bjike.goddess.financeinit.dto.WithUnitDTO;
import com.bjike.goddess.financeinit.entity.WithUnit;
import com.bjike.goddess.financeinit.excel.SonPermissionObject;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.WithUnitTO;

import java.util.List;

/**
 * 往来单位业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WithUnitSer extends Ser<WithUnit, WithUnitDTO> {
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
     * 往来单位列表总条数
     */
    default Long countWith(WithUnitDTO withUnitDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取往来单位
     *
     * @return class WithUnitBO
     */
    default WithUnitBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 往来单位列表
     *
     * @return class WithUnitBO
     */
    default List<WithUnitBO> listWith(WithUnitDTO withUnitDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param withUnitTO 往来单位
     * @return class WithUnitBO
     */
    default WithUnitBO addWith(WithUnitTO withUnitTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param withUnitTO 往来单位
     * @return class WithUnitBO
     */
    default WithUnitBO editWith(WithUnitTO withUnitTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 往来单位id
     */
    default void deleteWith(String id) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;
}