package com.bjike.goddess.function.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.function.dto.FunctionDTO;
import com.bjike.goddess.function.entity.Function;

/**
 * 模块功能持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 11:07 ]
 * @Description: [ 模块功能持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FunctionRep extends JpaRep<Function, FunctionDTO> {

}