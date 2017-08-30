package com.bjike.goddess.datastore.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.datastore.dto.NumSpecificationDTO;
import com.bjike.goddess.datastore.entity.NumSpecification;

/**
 * 数据存储编号规范持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:35 ]
 * @Description: [ 数据存储编号规范持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NumSpecificationRep extends JpaRep<NumSpecification, NumSpecificationDTO> {

}