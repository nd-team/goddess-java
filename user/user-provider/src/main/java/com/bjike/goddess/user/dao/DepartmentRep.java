package com.bjike.goddess.user.dao;


import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.user.dto.DepartmentDTO;
import com.bjike.goddess.user.entity.Department;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: [部门持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DepartmentRep extends JpaRep<Department, DepartmentDTO> {

}
