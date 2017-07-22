package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.materialbuy.bo.DeviceTypeBO;
import com.bjike.goddess.materialbuy.dto.DeviceTypeDTO;
import com.bjike.goddess.materialbuy.entity.DeviceType;
import com.bjike.goddess.materialbuy.to.DeviceTypeTO;
import com.bjike.goddess.materialbuy.to.GuidePermissionTO;

import java.util.List;

/**
 * 设备类型业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 03:39 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DeviceTypeSer extends Ser<DeviceType, DeviceTypeDTO> {
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
     * 分页查询设备类型
     *
     * @return class DeviceTypeBO
     * @throws SerException
     */
    List<DeviceTypeBO> list(DeviceTypeDTO dto) throws SerException;

    /**
     * 保存设备类型
     *
     * @param to 设备类型to
     * @return class DeviceTypeBO
     * @throws SerException
     */
    DeviceTypeBO save(DeviceTypeTO to) throws SerException;

    /**
     * 根据id删除设备类型
     *
     * @param id 设备类型唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新设备类型
     *
     * @param to 设备类型to
     * @throws SerException
     */
    void update(DeviceTypeTO to) throws SerException;

    /**
     * 查询所有设备名称
     *
     * @throws SerException
     */
    List<String> findAllDeviceNames() throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(DeviceTypeDTO dto) throws SerException;
}