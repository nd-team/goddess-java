package com.bjike.goddess.devicerepair.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.excel.SonPermissionObject;
import com.bjike.goddess.devicerepair.to.DeviceRepairTO;
import com.bjike.goddess.devicerepair.to.FetchDeviceTO;
import com.bjike.goddess.devicerepair.to.GuidePermissionTO;
import com.bjike.goddess.devicerepair.to.WelfareAuditTO;
import com.bjike.goddess.devicerepair.type.AuditState;
import com.bjike.goddess.devicerepair.type.MaterialState;

import java.util.List;

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
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
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
     * 项目经理审核状态
     *
     * @param id           设备维修唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws SerException
     */
    void pmAudit(String id, AuditState pmAuditState) throws SerException;

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


    /**
     * 获取所有组织结构中的部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findAddAllDetails() throws SerException {
        return null;
    }

    /**
     * 获取所有用户
     *
     * @return
     * @throws SerException
     */
    default List<String> findallMonUser() throws SerException {
        return null;
    }
    /**
     * 获取所有维修状态
     *
     * @return
     * @throws SerException
     */
    default List<MaterialState> findDeviceStatus(String[] intervalTime) throws SerException {
        return null;
    }
    /**
     * 根据维修状态获取所有地区
     *
     * @return
     * @throws SerException
     */
    default List<String> findAreaByStatus(String[] intervalTime,MaterialState materialState) throws SerException {
        return null;
    }
    /**
     * 根据维修状态地区获取部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findProjectByStaAnAr(String[] intervalTime,MaterialState materialState,String area) throws SerException {
        return null;
    }
    /**
     * 根据维修状态地区获取信息
     *
     * @return
     * @throws SerException
     */
    default List<DeviceRepairBO> findByStaAnAr(String[] intervalTime,MaterialState materialState,String area,String projectGroup) throws SerException {
        return null;
    }
    /**
     * 获取所有是否为保修期状态
     *
     * @return
     * @throws SerException
     */
    default List<Boolean> findBool(String[] intervalTime) throws SerException {
        return null;
    }
    /**
     * 根据是否为保修期状态查询地区
     *
     * @return
     * @throws SerException
     */
    default List<String> findAreaByBool(String[] intervalTime,Boolean whetherWarranty) throws SerException {
        return null;
    }
    /**
     * 根据是否为保修期状态和地区查询部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findProjByBoArea(String[] intervalTime,String area,Boolean whetherWarranty) throws SerException {
        return null;
    }
    /**
     * 根据是否为保修期状态和地区和部门查询信息
     *
     * @return
     * @throws SerException
     */
    default List<DeviceRepairBO> findByBoAnArDep(String[] intervalTime,String area,Boolean whetherWarranty,String projectGroup) throws SerException {
        return null;
    }
}