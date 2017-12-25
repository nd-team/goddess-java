package com.bjike.goddess.business.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.entity.BusinessRegister;

/**
 * 工商注册持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessRegisterRep extends JpaRep<BusinessRegister, BusinessRegisterDTO> {

}