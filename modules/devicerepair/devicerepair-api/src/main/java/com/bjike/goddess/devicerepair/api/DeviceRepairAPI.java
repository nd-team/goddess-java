package com.bjike.goddess.devicerepair.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.to.DeviceRepairTO;
import com.bjike.goddess.devicerepair.to.FetchDeviceTO;
import com.bjike.goddess.devicerepair.to.WelfareAuditTO;

import java.util.List;
import java.util.Map;

/**
 * 设备维修业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DeviceRepairAPI {

    /**
     * 根据id查询设备维修
     *
     * @param id 设备维修唯一标识
     * @return class DeviceRepairBO
     * @throws SerException
     */
    DeviceRepairBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 设备维修dto
     * @throws SerException
     */
    Long count(DeviceRepairDTO dto) throws SerException;

    /**
     * 分页查询设备维修
     *
     * @param dto 设备维修dto
     * @return class DeviceRepairBO
     * @throws SerException
     */
    List<DeviceRepairBO> list(DeviceRepairDTO dto) throws SerException;

    /**
     * 保存设备维修
     *
     * @param to 设备维修to
     * @return class DeviceRepairBO
     * @throws SerException
     */
    DeviceRepairBO save(DeviceRepairTO to) throws SerException;

    /**
     * 根据id删除设备维修
     *
     * @param id 设备维修唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新设备维修
     *
     * @param to 设备维修to
     * @throws SerException
     */
    void update(DeviceRepairTO to) throws SerException;

    /**
     * 福利模块审核
     *
     * @param to 福利模块审核to
     * @throws SerException
     */
    void welfareAudit(WelfareAuditTO to) throws SerException;

    /**
     * 设备报废
     *
     * @param id          设备维修唯一标识
     * @param deviceIssue 设备出现的问题
     * @throws SerException
     */
    void deviceScrap(String id, String deviceIssue) throws SerException;

    /**
     * 取回设备
     *
     * @param to
     * @throws SerException
     */
    void fetchDevice(FetchDeviceTO to) throws SerException;

}