package com.bjike.goddess.driverinfo.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.driverinfo.bo.DriverInfoBO;
import com.bjike.goddess.driverinfo.dto.DriverInfoDTO;
import com.bjike.goddess.driverinfo.to.DriverInfoTO;

import java.util.List;

/**
 * @Author: [Jason]
 * @Date: [17-3-13 上午11:19]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DriverInfoAPI {

    /**
     * 保存租车协议、车辆信息管理司机信息
     *
     * @param bo 司机信息业务model
     * @return 司机信息业务model
     * @throws SerException 业务异常
     */
    DriverInfoBO saveDriverInfo(DriverInfoTO bo) throws SerException;

    /**
     * 修改租车协议、车辆信息管理司机信息
     *
     * @param bo 司机信息业务model
     * @return 司机信息业务model
     * @throws SerException 业务异常
     */
    DriverInfoBO updateDriverInfo(DriverInfoTO bo) throws SerException;

    /**
     * 修改id对应司机信息状态
     *
     * @param id 司机信息id
     * @throws SerException 业务异常
     */
    void updateStatus(String id) throws SerException;

    /**
     * 保存、修改审核及审核意见
     *
     * @param bo 司机信息业务model
     * @return 司机信息业务model
     * @throws SerException 业务异常
     */
    DriverInfoBO updateAudit(DriverInfoTO bo) throws SerException;

    /**
     * 页面数据查询
     *
     * @param dto 页面分页数据传输对象
     * @return 业务传输类
     * @throws SerException 业务异常
     */
    List<DriverInfoBO> pageList(DriverInfoDTO dto, String type) throws SerException;

    /**
     * 删除司机信息
     *
     * @param id
     */
    void remove(String id) throws SerException;

    /**
     * 根据司机名称查询司机信息
     *
     * @param driverName 司机名称
     * @return 司机信息
     */
    DriverInfoBO findByDriver(String driverName) throws SerException;
}
