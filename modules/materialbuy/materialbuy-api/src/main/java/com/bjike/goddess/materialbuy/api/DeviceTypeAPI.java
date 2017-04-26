package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialbuy.bo.DeviceTypeBO;
import com.bjike.goddess.materialbuy.dto.DeviceTypeDTO;
import com.bjike.goddess.materialbuy.to.DeviceTypeTO;

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
public interface DeviceTypeAPI {

    /**
     * 根据id查询设备类型
     *
     * @param id 设备类型唯一标识
     * @return class DeviceTypeBO
     * @throws SerException
     */
    DeviceTypeBO findById(String id) throws SerException;

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

}