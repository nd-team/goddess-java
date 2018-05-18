package com.bjike.goddess.customerplatform.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customerplatform.bo.OwnerBO;
import com.bjike.goddess.customerplatform.dto.OwnerDTO;
import com.bjike.goddess.customerplatform.entity.Owner;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.customerplatform.to.OwnerTO;

import java.util.List;

/**
 * 业主业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:27 ]
 * @Description: [ 业主业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OwnerSer extends Ser<Owner, OwnerDTO> {

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
     * 保存
     *
     * @param to
     * @throws SerException
     */
    default void save(OwnerTO to) throws SerException {
        return;
    }

    /**
     * 修改
     *
     * @param to
     * @throws SerException
     */
    default void update(OwnerTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 列表数据
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<OwnerBO> maps(OwnerDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取中标单位数据
     *
     * @param id
     * @throws SerException
     */
    default OwnerBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal(OwnerDTO dto) throws SerException {
        return null;
    }
    /**
     * 获取所有省份
     *
     * @throws SerException
     */
    List<String> getProvinces() throws SerException;

    /**
     * 获取所有市
     *
     * @throws SerException
     */
    List<String> getCity(String provinces) throws SerException;

    /**
     * 获取所有区
     *
     * @throws SerException
     */
    List<String> getArea(String provinces,String city) throws SerException;
}