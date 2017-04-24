package com.bjike.goddess.function.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.function.dto.UserFunctionDTO;
import com.bjike.goddess.function.entity.UserFunction;

/**
 * 用户功能持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 01:56 ]
 * @Description: [ 用户功能持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface UserFunctionRep extends JpaRep<UserFunction, UserFunctionDTO> {

}