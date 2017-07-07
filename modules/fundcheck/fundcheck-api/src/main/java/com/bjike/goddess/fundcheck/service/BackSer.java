package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.entity.Back;
import com.bjike.goddess.fundcheck.excel.SonPermissionObject;
import com.bjike.goddess.fundcheck.to.BackTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;

import java.util.List;

/**
 * 回款业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:48 ]
 * @Description: [ 回款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BackSer extends Ser<Back, BackDTO> {
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
     * 回款列表总条数
     */
    default Long count(BackDTO backDTO) throws SerException {
        return null;
    }

    /**
     * 一个回款
     *
     * @return class BackBO
     */
    default BackBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 回款
     *
     * @param backDTO 回款dto
     * @return class BackBO
     * @throws SerException
     */
    default List<BackBO> findListBack(BackDTO backDTO) throws SerException {
        return null;
    }

    /**
     * 添加回款
     *
     * @param backTO 回款数据to
     * @return class BackBO
     * @throws SerException
     */
    default BackBO insert(BackTO backTO) throws SerException {
        return null;
    }

    /**
     * 编辑回款
     *
     * @param backTO 回款数据to
     * @return class BackBO
     * @throws SerException
     */
    default BackBO edit(BackTO backTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除回款
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 回款
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class BackBO
     * @throws SerException
     */
    default List<BackBO> backinfo(String startTime, String endTime) throws SerException {
        return null;
    }

}