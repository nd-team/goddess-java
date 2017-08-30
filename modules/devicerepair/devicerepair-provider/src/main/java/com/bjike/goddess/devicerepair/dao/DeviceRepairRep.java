package com.bjike.goddess.devicerepair.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.entity.DeviceRepair;

/**
 * 设备维修持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DeviceRepairRep extends JpaRep<DeviceRepair, DeviceRepairDTO> {

}