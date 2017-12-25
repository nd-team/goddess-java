package com.bjike.goddess.managepromotion.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.entity.EmployeeFunctionLevel;

/**
 * 员工职能定级持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EmployeeFunctionLevelRep extends JpaRep<EmployeeFunctionLevel, EmployeeFunctionLevelDTO> {

}