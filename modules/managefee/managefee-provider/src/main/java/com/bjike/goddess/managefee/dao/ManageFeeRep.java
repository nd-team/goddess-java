package com.bjike.goddess.managefee.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managefee.dto.ManageFeeDTO;
import com.bjike.goddess.managefee.entity.ManageFee;

/**
 * 管理费持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ManageFeeRep extends JpaRep<ManageFee, ManageFeeDTO> {

}