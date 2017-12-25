package com.bjike.goddess.festival.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.festival.dto.AreaRelationerDTO;
import com.bjike.goddess.festival.entity.AreaRelationer;

/**
 * 各地区紧急联系人持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:13 ]
 * @Description: [ 各地区紧急联系人持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AreaRelationerRep extends JpaRep<AreaRelationer, AreaRelationerDTO> {

}