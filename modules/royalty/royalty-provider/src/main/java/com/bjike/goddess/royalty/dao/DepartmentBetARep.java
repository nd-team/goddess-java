package com.bjike.goddess.royalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.royalty.dto.DepartmentBetADTO;
import com.bjike.goddess.royalty.entity.DepartmentBetA;
import com.bjike.goddess.royalty.entity.DepartmentBetB;

/**
 * 部门间对赌表A持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表A持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DepartmentBetARep extends JpaRep<DepartmentBetA, DepartmentBetADTO> {

}