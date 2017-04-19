package com.bjike.goddess.dispatchcar.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.entity.DispatchCarInfo;

/**
 * 出车记录持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DispatchCarInfoRep extends JpaRep<DispatchCarInfo, DispatchCarInfoDTO> {

}