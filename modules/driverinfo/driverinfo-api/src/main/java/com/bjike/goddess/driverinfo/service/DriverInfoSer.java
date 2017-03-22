package com.bjike.goddess.driverinfo.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.driverinfo.bo.DriverInfoBO;
import com.bjike.goddess.driverinfo.dto.DriverInfoDTO;
import com.bjike.goddess.driverinfo.entity.DriverInfo;
import com.bjike.goddess.driverinfo.to.DriverInfoTO;

import java.util.List;

/**
 * 租车协议、车辆信息管理业务接口
 *
 * @Author: [Jason]
 * @Date: [17-3-8 上午9:27]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DriverInfoSer extends Ser<DriverInfo, DriverInfoDTO> {

    /**
     * 保存租车协议、车辆信息管理司机信息
     *
     * @param to 司机信息业务model
     * @return 司机信息业务model
     * @throws SerException 业务异常
     */
    DriverInfoBO saveDriverInfo(DriverInfoTO to) throws SerException;

    /**
     * 修改租车协议、车辆信息管理司机信息
     *
     * @param to 司机信息业务model
     * @return 司机信息业务model
     * @throws SerException 业务异常
     */
    DriverInfoBO updateDriverInfo(DriverInfoTO to) throws SerException;

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
     * @param to 司机信息业务model
     * @return 司机信息业务model
     * @throws SerException 业务异常
     */
    DriverInfoBO updateAudit(DriverInfoTO to) throws SerException;

    /**
     * 页面数据查询
     *
     * @param dto 页面分页数据传输对象
     * @return 业务传输类
     * @throws SerException 业务异常
     */
    List<DriverInfoBO> pageList(DriverInfoDTO dto, String type) throws SerException;

    /**
     * 每半个月定时提醒是否需要新增司机招聘
     */
    void isNeedDriver();
}
