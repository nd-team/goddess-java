package com.bjike.goddess.driverinfo.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.driverinfo.dto.DriverInfoDTO;
import com.bjike.goddess.driverinfo.entity.DriverInfo;

/**
 * 司机信息JPA规范接口
 * @Author: [Jason]
 * @Date: [17-3-8 上午9:42]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IDriverInfoRep extends JpaRep<DriverInfo,DriverInfoDTO> {
}
