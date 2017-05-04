package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.workjoin.bo.DeviceJoinBO;
import com.bjike.goddess.workjoin.bo.JoinInfoBO;
import com.bjike.goddess.workjoin.dto.DeviceJoinDTO;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.entity.DeviceJoin;
import com.bjike.goddess.workjoin.to.DeviceJoinTO;
import com.bjike.goddess.workjoin.to.JoinInfoTO;

import java.util.List;

/**
 * 设备交接业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DeviceJoinSer extends Ser<DeviceJoin, DeviceJoinDTO> {

    /**
     * 设备交接列表总条数
     */
    default Long countDeviceJoin(DeviceJoinDTO deviceJoinDTO) throws SerException {
        return null;
    }

    /**
     * 一个设备交接
     *
     * @return class DeviceJoinBO
     */
    default DeviceJoinBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 设备交接
     *
     * @param deviceJoinDTO 设备交接dto
     * @return class DeviceJoinBO
     * @throws SerException
     */
    default List<DeviceJoinBO> findListDeviceJoin(DeviceJoinDTO deviceJoinDTO) throws SerException {
        return null;
    }

    /**
     * 添加设备交接
     *
     * @param deviceJoinTO 设备交接数据to
     * @return class DeviceJoinBO
     * @throws SerException
     */
    default DeviceJoinBO insertDeviceJoin(DeviceJoinTO deviceJoinTO) throws SerException {
        return null;
    }

    /**
     * 编辑设备交接
     *
     * @param deviceJoinTO 设备交接数据to
     * @return class DeviceJoinBO
     * @throws SerException
     */
    default DeviceJoinBO editDeviceJoin(DeviceJoinTO deviceJoinTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除设备交接
     *
     * @param id
     * @throws SerException
     */
    default void removeDeviceJoin(String id) throws SerException {

    }

    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }

    /**
     * 下载
     */
    default void download() throws SerException {
        return;

    }

}