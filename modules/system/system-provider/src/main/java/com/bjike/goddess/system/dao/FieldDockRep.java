package com.bjike.goddess.system.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.system.dto.FieldDockDTO;
import com.bjike.goddess.system.entity.FieldDock;

/**
 * 字段对接持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 11:43 ]
 * @Description: [ 字段对接持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FieldDockRep extends JpaRep<FieldDock, FieldDockDTO> {

}