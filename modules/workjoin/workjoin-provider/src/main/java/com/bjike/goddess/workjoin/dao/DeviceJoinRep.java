package com.bjike.goddess.workjoin.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workjoin.dto.DeviceJoinDTO;
import com.bjike.goddess.workjoin.entity.DeviceJoin;

/**
 * 设备交接持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DeviceJoinRep extends JpaRep<DeviceJoin, DeviceJoinDTO> {

}